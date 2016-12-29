package cn.lawliex.ask.answer.detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerDetailFragment extends Fragment implements AnswerDetailContract.View {
    AnswerDetailContract.Presenter presenter;
    @Override
    public void showAnswer(Answer answer) {

    }

    @Override
    public void setPresenter(AnswerDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
