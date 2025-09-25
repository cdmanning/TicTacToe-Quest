package com.starswept.tictactoequest;

import com.github.jinatonic.confetti.Utils;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.github.jinatonic.confetti.ConfettiManager;
import com.github.jinatonic.confetti.ConfettiSource;
import com.github.jinatonic.confetti.ConfettoGenerator;
import com.github.jinatonic.confetti.confetto.BitmapConfetto;
import com.github.jinatonic.confetti.confetto.Confetto;
import java.util.Random;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class game_victory_screen extends Fragment {
    public game_victory_screen() {
        // Required empty public constructor
    }
    View view;
    String playerOneNameIs, playerTwoNameIs;
    String winningPlayerIs;
    TextView WinnerNameField;
    Button new_game_button, exit_button;
    int orange_confetti = Color.rgb(236,188,154);
    int blue_confetti = Color.rgb(177,201,219);
    int yellow_confetti = Color.rgb(177,201,219);
    int green_confetti = Color.rgb(181,225,203);
    int purple_confetti = Color.rgb(230,154,227);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game_victory_screen, container, false);
        WinnerNameField = (TextView) view.findViewById(R.id.winnerName);
        Bundle transferBundle=getArguments();
        if(transferBundle != null){
            winningPlayerIs = transferBundle.getString("winningPlayer", null);
            playerOneNameIs = transferBundle.getString("playerOneNameIs", null);
            playerTwoNameIs = transferBundle.getString("playerTwoNameIs", null);
            WinnerNameField.setText(winningPlayerIs);
        }
        exit_button = view.findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new home();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment ).commit();
            }
        });
        new_game_button = view.findViewById(R.id.new_game_button);
        new_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(transferBundle != null) {
                    transferBundle.putString("playerOneNameIs", playerOneNameIs);
                    transferBundle.putString("playerTwoNameIs", playerTwoNameIs);
                    multiplayer_game playerNames = new multiplayer_game();
                    playerNames.setArguments(transferBundle);
                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, playerNames).commit();
                }
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ViewGroup container = view.findViewById(R.id.victory_screen_layout);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Resources res = getResources();
                int emissionDuration = res.getInteger(R.integer.confetti_emission_duration);
                int emissionRate = res.getInteger(R.integer.confetti_emission_rate);
                int velocityXMin = res.getInteger(R.integer.confetti_velocity_x_min);
                int velocityXMax = res.getInteger(R.integer.confetti_velocity_x_max);
                int velocityYMin = res.getInteger(R.integer.confetti_velocity_y_min);
                int velocityYMax = res.getInteger(R.integer.confetti_velocity_y_max);
                int targetVelocityY = res.getInteger(R.integer.confetti_target_velocity_y);
                int accelerationYBase = res.getInteger(R.integer.confetti_acceleration_y_base);
                int accelerationYVariance = res.getInteger(R.integer.confetti_acceleration_y_variance);
                int rotationalVelocityMin = res.getInteger(R.integer.confetti_rotational_velocity_min);
                int rotationalVelocityMax = res.getInteger(R.integer.confetti_rotational_velocity_max);
                int sourceYOffset = res.getInteger(R.integer.confetti_source_y_offset);
                final int[] colors = new int[] {orange_confetti, yellow_confetti, green_confetti, blue_confetti, purple_confetti};
                final int[] sizes = new int[] {25, 35};
                final ConfettoGenerator generator = new ConfettoGenerator() {
                    @Override
                    public Confetto generateConfetto(Random random) {
                        int sizeIndex = random.nextInt(sizes.length);
                        int size = sizes[sizeIndex];
                        int width = size + random.nextInt(size);
                        Bitmap bitmap = Bitmap.createBitmap(width, size, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        Paint paint = new Paint();
                        paint.setColor(colors[random.nextInt(colors.length)]);
                        canvas.drawRect(0, 0, width, size, paint);
                        return new BitmapConfetto(bitmap);
                    }
                };
                final ConfettiSource source = new ConfettiSource(0, sourceYOffset, container.getWidth(), sourceYOffset);
                new ConfettiManager(getContext(), generator, source, container)
                        .setEmissionDuration(emissionDuration)
                        .setEmissionRate(emissionRate)
                        .enableFadeOut(Utils.getDefaultAlphaInterpolator())
                        .setVelocityX(velocityXMin, velocityXMax)
                        .setVelocityY(velocityYMin, velocityYMax)
                        .setAccelerationY(accelerationYBase, accelerationYVariance)
                        .setTargetVelocityY(targetVelocityY)
                        .setRotationalVelocity(rotationalVelocityMin, rotationalVelocityMax)
                        .setTouchEnabled(true)
                        .animate();
            }
        });
    }
}