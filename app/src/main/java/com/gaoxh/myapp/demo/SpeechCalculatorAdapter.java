package com.gaoxh.myapp.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gaoxh.myapp.R;
import com.gaoxh.myapp.di.ContextType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by devil on 2017/3/7.
 */

public class SpeechCalculatorAdapter extends RecyclerView.Adapter<SpeechCalculatorAdapter.SpeechCalculatorViewHolder> {

    private Context context;

    private List<String> instructionList;

    @Inject
    public SpeechCalculatorAdapter(@ContextType(ContextType.Type.Activity) Context context) {
        this.context = context;
        this.instructionList=new ArrayList<>();
    }

    @Override
    public SpeechCalculatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_speech_calculator,parent,false);
        return new SpeechCalculatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpeechCalculatorViewHolder holder, int position) {
        holder.tvInstruction.setText(instructionList.get(position));
    }

    @Override
    public int getItemCount() {
        return instructionList!=null?instructionList.size():0;
    }


    public void addInstruction(String instruction) {
        this.instructionList.add(instruction);
        notifyDataSetChanged();
    }

    public void clear(){
        this.instructionList.clear();
        notifyDataSetChanged();
    }

    public static class SpeechCalculatorViewHolder extends ViewHolder {
        @BindView(R.id.tv_speech_calculator_instruction)
        TextView tvInstruction;
        public SpeechCalculatorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
