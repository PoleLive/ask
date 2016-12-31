package cn.lawliex.ask;

import android.app.Activity;

/**
 * Created by Terence on 2016/12/27.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    Activity getActivity();
}
