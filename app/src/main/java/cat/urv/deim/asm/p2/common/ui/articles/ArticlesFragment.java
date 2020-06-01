package cat.urv.deim.asm.p2.common.ui.articles;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.p2.common.R;
import cat.urv.deim.asm.p3.shared.ArticleDetailActivity;
import cat.urv.deim.asm.p3.shared.adapters.ArticlesListAdapter;

public class ArticlesFragment extends Fragment {

    private ArticlesViewModel articlesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_articles, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.card_list);
        
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Load data from commanagerdc
        final DataProvider dataProvider = DataProvider.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext(),R.raw.faqs,R.raw.news,R.raw.articles,R.raw.events,R.raw.calendar);

        // specify an adapter (see also next example)
        final ArticlesListAdapter cardListAdapter = new ArticlesListAdapter(dataProvider.getArticles());
        cardListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtra("Article Position", recyclerView.getChildAdapterPosition(v));
                //Log.i("DemoRecView", "Element " + recyclerView.getChildAdapterPosition(v) + " pressed.");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(cardListAdapter);

        return root;
    }
}
