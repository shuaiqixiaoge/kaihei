package com.xiaohui.android.activity.xx;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.BarManager;
import com.xiaohui.android.BaseActivity;
import com.xiaohui.android.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class FriendSearchActivity extends BaseActivity {
    @BindView(R.id.add_search)
    EditText addSearch;
    @BindView(R.id.add_search_num)
    TextView addSearchNum;
    @BindView(R.id.add_search_content)
    LinearLayout addSearchContent;


    @OnClick(R.id.onClick_CANCEL)
    public void onClick_CANCEL() {
        finish();
    }


    @Override
    public void onActCreate(Bundle savedInstanceState) {
        BarManager.setBarColor(this, ContextCompat.getColor(this, R.color.main_gray_bg), true);
        addSearch.addTextChangedListener(watcher);
        addSearchContent.setOnClickListener(onClick_Search);
    }

    public View.OnClickListener onClick_Search = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(SearchResultActivity.class);
        }
    };

    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                addSearchContent.setVisibility(View.VISIBLE);
            } else {
                addSearchContent.setVisibility(View.GONE);
            }
            addSearchNum.setText(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public int resLayoutID() {
        return R.layout.activity_friend_search;
    }

    @Override
    public void initData() {

    }

}
