package cn.lawliex.ask.timeline;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Feed;

/**
 * Created by Terence on 2017/1/6.
 */

public class TimeLineContract {
    public interface Presenter extends BasePresenter{
        void loadFeeds();
        void collect();
        void follow();
    }
    public interface View extends BaseView<Presenter> {
        void showFeedsView(List<Feed> feeds);
        void update();
        void showErrorMessage(String errMsg);
    }
}
