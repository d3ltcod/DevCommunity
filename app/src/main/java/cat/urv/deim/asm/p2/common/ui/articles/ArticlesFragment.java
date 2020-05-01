package cat.urv.deim.asm.p2.common.ui.articles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cat.urv.deim.asm.p2.common.R;

public class ArticlesFragment extends Fragment {

    private ArticlesViewModel articlesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_articles, container, false);

        return root;
    }
}
