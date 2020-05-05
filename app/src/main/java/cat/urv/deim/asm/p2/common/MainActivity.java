package cat.urv.deim.asm.p2.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import cat.urv.deim.asm.p2.common.ui.articles.ArticlesFragment;
import cat.urv.deim.asm.p2.common.ui.calendar.CalendarFragment;
import cat.urv.deim.asm.p2.common.ui.events.EventsFragment;
import cat.urv.deim.asm.p2.common.ui.news.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_news, R.id.nav_articles, R.id.nav_events, R.id.nav_calendar)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                boolean end = false;

                if (id == R.id.nav_news) {
                    Fragment newsFragment = new NewsFragment();
                    show(newsFragment);
                } else if (id == R.id.nav_articles) {
                    Fragment articlesFragment = new ArticlesFragment();
                    show(articlesFragment);
                } else if (id == R.id.nav_events) {
                    Fragment eventsFragment = new EventsFragment();
                    show(eventsFragment);
                } else if (id == R.id.nav_calendar) {
                    Fragment calendarFragment = new CalendarFragment();
                    show(calendarFragment);
                } else if (id == R.id.nav_profile) {
                    SharedPreferences sharedPref = getApplicationContext()
                            .getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    Class activity;

                    if (sharedPref.getBoolean(getString(R.string.preference_logged_user), false))
                        activity = ProfileActivity.class;
                    else {
                        activity = LoginActivity.class;
                        end = true;
                    }

                    Intent intent = new Intent(MainActivity.this, activity);
                    startActivity(intent);
                    if (end) finish();

                } else return false;

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    private void show(Fragment fragment) {

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();

        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
