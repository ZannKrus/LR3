package com.example.lr3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4, button5;
    private int defaultTextColor; // переменная для возврата цвета текста по умолчанию


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        init();

        button1.setOnClickListener(v -> {
            Toast.makeText(this, "Кнопка номер 1 нажата", Toast.LENGTH_SHORT).show();
        });

        button2.setOnClickListener(v -> {
            Toast.makeText(this, "Кнопка номер 2 нажата", Toast.LENGTH_LONG).show();
        });

        button3.setOnClickListener(v -> {
            showConfirmDialog();
        });

        button4.setOnClickListener(v -> {
            showQuiz();
        });

        button5.setOnClickListener(v -> {
            showAllButtons();
        });
    }

    private void init() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        defaultTextColor = button1.getCurrentTextColor(); // сохраняем цвет текста по умолчанию
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Если цвет уже изменен, предлагаем вернуть обратно
        if (button1.getCurrentTextColor() == Color.RED) {
            builder.setTitle("Кнопка 3")
                    .setMessage("Вернуть цвет текста кнопок к исходному?")
                    .setIcon(R.drawable.test_icon)
                    .setCancelable(false)
                    .setPositiveButton("Да", (dialog, which) -> {
                        resetButtonTextColor();
                        dialog.dismiss();
                    })
                    .setNegativeButton("Нет", (dialog, which) -> {
                        Toast.makeText(this, "Действие отменено", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    });
        } else {
            builder.setTitle("Кнопка 3")
                    .setMessage("Вы уверены, что хотите изменить цвет текста кнопок?")
                    .setIcon(R.drawable.test_icon)
                    .setCancelable(false)
                    .setPositiveButton("Да", (dialog, which) -> {
                        changeButtonTextColor();
                        dialog.dismiss();
                    })
                    .setNegativeButton("Нет", (dialog, which) -> {
                        Toast.makeText(this, "Действие отменено", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //функция для смены цвета на красный
    private void changeButtonTextColor() {
        button1.setTextColor(Color.RED);
        button2.setTextColor(Color.RED);
        button3.setTextColor(Color.RED);
        button4.setTextColor(Color.RED);
    }
    //функция для возврата цвета по умолчанию
    private void resetButtonTextColor() {
        button1.setTextColor(defaultTextColor);
        button2.setTextColor(defaultTextColor);
        button3.setTextColor(defaultTextColor);
        button4.setTextColor(defaultTextColor);
    }

    private void showQuiz() {
        final String[] quizItems = {"Кит", "Акула", "Пингвин", "Летучая мышь", "Крокодил"};
        final boolean[] checkedItems = new boolean[quizItems.length];
        final ArrayList<Integer> userSelectedItems = new ArrayList<>();

        final ArrayList<Integer> correctAnswers = new ArrayList<>();
        correctAnswers.add(0);
        correctAnswers.add(3);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Какие из этих животных млекопитающие")
                .setMultiChoiceItems(quizItems, checkedItems, (dialog, which, isChecked) -> {
                    if (isChecked) {
                        userSelectedItems.add(which);
                    } else {
                        userSelectedItems.remove(Integer.valueOf(which));
                    }
                })
                .setPositiveButton("Проверить", (dialog, which) -> {
                    Collections.sort(userSelectedItems);
                    Collections.sort(correctAnswers);

                    if (userSelectedItems.equals(correctAnswers)) {
                        Toast.makeText(this, "Правильно!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Неправильно!", Toast.LENGTH_LONG).show();
                        hideAllButtons();
                    }
                })
                .setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //функция для скрытия всех кнопок
    private void hideAllButtons() {
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.VISIBLE);
    }
    //функция для показа всех кнопок
    private void showAllButtons() {
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.GONE);
    }
}