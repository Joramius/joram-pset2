package vanoosterum.joram_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int wordsAdded = 0;
    String[] words = new String[13];
    final String[] hints = {"adjective", "noun (plural)", "noun", "adjective", "place",
            "noun (plural)", "noun", "funny noise", "adjective", "noun", "adjective",
            "noun (plural)", "person's name"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputField = (EditText) findViewById(R.id.input);
        inputField.setHint(hints[wordsAdded]);

        // enable action button
        inputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    addWord(v);
                    handled = true;
                }
                return handled;
            }
        });

        TextView wordCounter = (TextView) findViewById(R.id.wordCounter);
        String countFormat = getResources().getString(R.string.wordCount);
        String count = String.format(countFormat, wordsAdded + 1);
        wordCounter.setText(count);
    }

    public void addWord(View view) {
        EditText inputField = (EditText) findViewById(R.id.input);
        String newWord = inputField.getText().toString();
        words[wordsAdded] = newWord;
        wordsAdded++;

        if (wordsAdded == 12) {
            Button next = (Button) findViewById(R.id.next);
            next.setText("Story!");
        }

        if (wordsAdded == 13) {
            makeStory();
            return;
        }
        else {
            inputField.setText("");
            inputField.setHint(hints[wordsAdded]);
        }

        // increment front end counter
        TextView wordCounter = (TextView) findViewById(R.id.wordCounter);
        String countFormat = getResources().getString(R.string.wordCount);
        String count = String.format(countFormat, wordsAdded + 1);
        wordCounter.setText(count);
    }

    public void makeStory() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("words", words);
        startActivity(intent);
        finish();
    }
}