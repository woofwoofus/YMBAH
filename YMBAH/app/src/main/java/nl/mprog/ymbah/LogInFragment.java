package nl.mprog.ymbah;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jan Geestman 10375406.
 */

// implements OnClickListener
public class LogInFragment extends DialogFragment{

    static LogInFragment newInstance(){
        LogInFragment lFrag = new LogInFragment();

//        Bundle args = new Bundle();
//        args.putInt("num", num);
//        oFrag.setArguments(args);

        return  lFrag;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log_in, container,
                false);
        Button closeButton = (Button) v.findViewById(R.id.CloseButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("LogIn");
                ft.remove(prev);
                ft.commit();
            }
        });
        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
