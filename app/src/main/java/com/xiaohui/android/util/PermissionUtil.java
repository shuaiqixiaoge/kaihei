package com.xiaohui.android.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.xiaohui.android.R;
import com.yanzhenjie.fragment.CompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwy on 2017/5/12.
 * package_name is com.xiaohui.android.util
 * 描述:权限管理
 */

public class PermissionUtil {
    public static final int PHONE_CALL = 0;
    public static final int CAMERA = 1;
    public static final int READ_CONTACTS = 2;

    private Activity activity;

    public PermissionUtil(Activity activity) {
        this.activity = activity;
    }


    @TargetApi(value = Build.VERSION_CODES.M)
    private List<String> findDeniedPermissions(String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (ContextCompat.checkSelfPermission(activity
                    , value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }


    @TargetApi(value = Build.VERSION_CODES.M)
    public boolean requestPermissions(int requestCode, String[] permissions) {
        List<String> deniedPermissions = findDeniedPermissions(permissions);
        if (deniedPermissions.size() > 0) {
            activity.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            return false;
        } else {
            return true;
        }
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults != null) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                int stringResId = -1;
                if (requestCode == PHONE_CALL) {
                    stringResId = R.string.permission_phone_call;
                }
                if (requestCode == CAMERA) {
                    stringResId = R.string.permission_camera;
                }
                if (requestCode == READ_CONTACTS) {
                    stringResId = R.string.permission_contacts;
                }
                createDialog(activity.getResources().getString(stringResId));
            }
        }
    }

    /**
     * 创建对话框
     *
     * @param message
     */
    public void createDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity).setTitle(activity.getResources().getString(R.string.permission_title));
        builder.setMessage(message);
        builder.setPositiveButton(activity.getResources().getString(R.string.permission_summit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
        builder.setNegativeButton(activity.getResources().getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

}
