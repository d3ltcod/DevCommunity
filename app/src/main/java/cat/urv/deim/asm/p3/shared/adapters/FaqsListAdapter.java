package cat.urv.deim.asm.p3.shared.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import cat.urv.deim.asm.libraries.commanagerdc.models.Faq;
import cat.urv.deim.asm.p2.common.R;

public class FaqsListAdapter extends RecyclerView.Adapter<FaqsListAdapter.FaqsListViewHolder> {

    private List<Faq> dataList;

    static class FaqsListViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView answerTextView;
        Button arrowButton;
        View expandableView;

        FaqsListViewHolder(@NonNull View itemView) {
            super(itemView);
            expandableView = itemView.findViewById(R.id.expandable_view);
            titleTextView = itemView.findViewById(R.id.title_faq_text);
            answerTextView = itemView.findViewById(R.id.faq_answer_text);
            arrowButton = itemView.findViewById(R.id.arrow_btn);

            arrowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandableView.getVisibility()==View.GONE){
                        expandableView.setVisibility(View.VISIBLE);
                        arrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up);
                    } else {
                        expandableView.setVisibility(View.GONE);
                        arrowButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_right);
                    }
                }
            });
        }
    }

    public FaqsListAdapter(List<Faq>  dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FaqsListAdapter.FaqsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_view,parent,false);
        return new FaqsListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqsListAdapter.FaqsListViewHolder faqsListViewHolder, int position) {
        Faq faq = dataList.get(position);
        faqsListViewHolder.titleTextView.setText(faq.getTitle());
        faqsListViewHolder.answerTextView.setText(faq.getBody());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}