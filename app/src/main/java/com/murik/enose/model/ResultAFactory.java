package com.murik.enose.model;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.murik.enose.Const;
import com.murik.enose.R;
import com.murik.enose.dto.DataByMaxParcelable;
import com.murik.enose.model.resultbyMaxValue.ResultByMask;

import java.util.ArrayList;

public abstract class ResultAFactory {

    private ArrayList<ResultBySens> A = new ArrayList<>();
    private ArrayList<Integer> maxSensResult = new ArrayList<>();
    private ResultByMask resultByMask;
    private DataByMaxParcelable inputData;

    private Context context;

    public ResultAFactory(DataByMaxParcelable inputData, int hand, Context context) {

        try {
            if (hand == Const.LEFT_HAND) {
                this.maxSensResult = inputData.getLeftHandDataSensor();
            } else if(hand == Const.RIGHT_HAND){
                this.maxSensResult = inputData.getRightHandDataSensor();
            } else if(hand == Const.MEAN_HAND && inputData.getDifferenceArea() < 15.4) {
                for(int i = 0; i < inputData.getRightHandDataSensor().size(); i++){
                    this.maxSensResult.add((inputData.getRightHandDataSensor().get(i)
                            + inputData.getLeftHandDataSensor().get(i))/2);
                }
            }
            this.inputData = inputData;
            this.resultByMask = new ResultByMask(maxSensResult);

            this.context = context;
        } catch (Exception e){
            return;
        }
    }

    public abstract boolean calculateResultA();

    public ArrayList<ResultBySens> getA() {
        return A;
    }

    private ArrayList<ResultBySens> sortA(){
        ArrayList<ResultBySens> newAArr = new ArrayList<>();

        int[] seqColorsToSort = new int[]{
                ContextCompat.getColor(context, R.color.colorResultBurgundy),
                ContextCompat.getColor(context, R.color.colorResultRed),
                ContextCompat.getColor(context, R.color.colorResultYellow),
                ContextCompat.getColor(context, R.color.colorPrimaryDark),
                ContextCompat.getColor(context, R.color.colorResultBlue),
                ContextCompat.getColor(context, R.color.colorResultCrimson),
                ContextCompat.getColor(context, R.color.colorResultGreen),
                Color.GRAY,
                Color.WHITE
        };

        for(int color: seqColorsToSort){
            for(ResultBySens a : A){
                if(a.getViewColor() == color){
                    newAArr.add(a);
                }
            }

        }
        return  newAArr;
    }

    protected ArrayList<Integer> getMaxSensResult() {
        return maxSensResult;
    }

    protected ResultByMask getResultByMask() {
        return resultByMask;
    }

    public DataByMaxParcelable getInputData() {
        return inputData;
    }

    public Context getContext() {
        return context;
    }
}
