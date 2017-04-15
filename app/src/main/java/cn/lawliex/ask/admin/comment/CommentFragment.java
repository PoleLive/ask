package cn.lawliex.ask.admin.comment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Comment;

/**
 * Created by Terence on 2016/12/31.
 */

public class CommentFragment extends Fragment implements CommentContract.View {
    CommentContract.Presenter presenter;

    List<Comment> comments;
    ListView listView;
    EditText editText;
    Button sendBn;
    @Override
    public void showComments(List<Comment> commentList) {
        this.comments = commentList;
        CommentListAdapter adapter = new CommentListAdapter(comments,this);
        listView.setAdapter(adapter);
    }

    @Override
    public void showErrorMsg(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public String getSendContent() {
        return editText.getText().toString();
    }

    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coment_fragment,container,false);
        listView = (ListView)view.findViewById(R.id.comment_list_view);
        sendBn = (Button)view.findViewById(R.id.comment_bn_send);
        editText = (EditText)view.findViewById(R.id.comment_edit_txt);

        sendBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length() == 0)
                    return;
                presenter.addComment();
                editText.setText("");
            }
        });
        return  view;
    }
    public void update(){
        presenter.start();
    }
    @Override
    public void onStart() {
        super.onStart();
    }
}
