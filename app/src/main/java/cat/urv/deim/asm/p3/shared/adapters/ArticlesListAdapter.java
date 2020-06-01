package cat.urv.deim.asm.p3.shared.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.p2.common.R;

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ArticleListViewHolder>
        implements View.OnClickListener{

    private List<Article> dataList;
    private View.OnClickListener listener;

    static class ArticleListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView subTitleTextView;
        TextView authorTextView;
        ImageView imageView;
        ToggleButton favoriteToggleButton;
        ToggleButton markToggleButton;

        ArticleListViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text);
            subTitleTextView = itemView.findViewById(R.id.subtitle_text);
            authorTextView = itemView.findViewById(R.id.author_text);
            imageView = itemView.findViewById(R.id.media_image);
            favoriteToggleButton = itemView.findViewById(R.id.favorite_button);
            markToggleButton = itemView.findViewById(R.id.mark_button);
        }
    }

    public ArticlesListAdapter(List<Article>  dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ArticlesListAdapter.ArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card_view,parent,false);

        rootView.setOnClickListener(this);

        return new ArticleListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesListAdapter.ArticleListViewHolder articleListViewHolder, int position) {
        Article article = dataList.get(position);

        articleListViewHolder.titleTextView.setText(article.getTitle());
        if(article.getAbstractText().length() > 50)
            articleListViewHolder.subTitleTextView.setText(article.getAbstractText().substring(0, 50));
        else
            articleListViewHolder.subTitleTextView.setText(article.getAbstractText());
        articleListViewHolder.authorTextView.setText(article.getAuthor());
        Picasso.get().load(article.getImageURL()).into(articleListViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
