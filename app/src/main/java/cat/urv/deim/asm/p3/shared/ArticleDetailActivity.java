package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

public class ArticleDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        int p = intent.getIntExtra("Article Position", -1);
        if (p < 0) {
            Toast.makeText(this, "There was an error obtaining the article.", Toast.LENGTH_SHORT).show();
            finish();
        }

        final DataProvider dataProvider = DataProvider.getInstance(Objects.requireNonNull(this)
                .getApplicationContext(), R.raw.faqs, R.raw.news, R.raw.articles, R.raw.events, R.raw.calendar);

        Article article = dataProvider.getArticles().get(p);

        article.loadImageToImageView((ImageView) findViewById(R.id.article_image));

        TextView title = findViewById(R.id.article_title);
        title.setText(article.getTitle());

        TextView tags = findViewById(R.id.article_tags);
        StringBuilder tagList = new StringBuilder();
        for (Tag t : article.getTags()) tagList.append(" ").append(t.getName());
        tags.setText(tagList);

        TextView body = findViewById(R.id.article_body);
        body.setText(article.getDescription());

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
