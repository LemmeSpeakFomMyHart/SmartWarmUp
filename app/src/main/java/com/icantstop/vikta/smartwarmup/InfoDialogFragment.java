package com.icantstop.vikta.smartwarmup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class InfoDialogFragment extends android.support.v4.app.DialogFragment {

    private ImageView mImageView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_info, null);

        mImageView = (ImageView) v.findViewById(R.id.image_view_channel);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri addressKanala = Uri.parse("https://www.youtube.com/user/DSmirnovable/featured");
                Intent perehodNaKanal = new Intent(Intent.ACTION_VIEW, addressKanala);
                startActivity(perehodNaKanal);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).
                setView(v).setTitle(R.string.title_dialog)
                .setPositiveButton(R.string.ok, null);
        return builder.create();
    }
}
