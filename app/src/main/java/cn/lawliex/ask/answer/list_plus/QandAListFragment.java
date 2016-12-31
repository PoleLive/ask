package cn.lawliex.ask.answer.list_plus;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/31.
 */

public class QandAListFragment extends Fragment implements QandAListContract.View {
    QandAListContract.Presenter presenter;
    ListView listView;
    List<Answer> answers;

    @Override
    public void update() {
        presenter.start();
    }

    @Override
    public void showAnswers(List<Answer> answerList) {
        answers = answerList;
        QandAListAdapter adapter = new QandAListAdapter(this,answers);
        listView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(QandAListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.q_and_a_list_fragment,container,false);
        listView = (ListView)view.findViewById(R.id.q_and_a_listview);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }
}
