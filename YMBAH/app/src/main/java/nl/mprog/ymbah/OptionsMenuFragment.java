package nl.mprog.ymbah;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Jan Geestman 10375406.
 */

// implements OnClickListener
public class OptionsMenuFragment extends DialogFragment{

    static OptionsMenuFragment newInstance(){

//        Bundle args = new Bundle();
//        args.putInt("num", num);
//        oFrag.setArguments(args);

        return new OptionsMenuFragment();
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_options, container,
                false);
        Button closeButton = (Button) v.findViewById(R.id.CloseButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("Options");
                ft.remove(prev);
                ft.commit();
            }
        });
        return v;
    }

}
