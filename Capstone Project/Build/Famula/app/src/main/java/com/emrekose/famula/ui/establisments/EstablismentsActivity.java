package com.emrekose.famula.ui.establisments;

import android.os.Bundle;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseActivity;
import com.emrekose.famula.databinding.ActivityEstablismentsBinding;

public class EstablismentsActivity extends BaseActivity<ActivityEstablismentsBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_establisments;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();

        if (dataBinding.establismentsContainer != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.establisments_container, EstablismentTypesListFragment.newInstance())
                    .commit();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(dataBinding.establistmentsToolbar);
    }
}
