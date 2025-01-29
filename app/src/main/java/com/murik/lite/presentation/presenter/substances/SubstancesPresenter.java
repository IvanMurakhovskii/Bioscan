package com.murik.lite.presentation.presenter.substances;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.dto.MeasureDataParcelable;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;
import com.murik.lite.model.SensorValueAttitudeFor30;
import com.murik.lite.model.substances.Substance;
import com.murik.lite.model.substances.ValueInRange;
import com.murik.lite.presentation.view.substances.SubstancesView;
import com.murik.lite.ui.fragment.substances.tab.SubstancePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.val;

import static com.murik.lite.service.Impl.BaseMeasureService.calculateT;
import static com.murik.lite.service.Impl.BaseMeasureService.getOneSensorResultParameters;
import static com.murik.lite.service.Impl.BaseMeasureService.round;
import static com.murik.lite.utils.ListUtils.getData;

@InjectViewState
public class SubstancesPresenter extends MvpPresenter<SubstancesView> {
    private MeasureDataParcelable data;
    private SubstancePage page;

    public void setData(MeasureDataParcelable data, int page) {
        this.data = data;
        this.page = SubstancePage.getByPageNumber(page);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        val algorithm = BluetoothDimensionAlgorithm.getByAlgorithmId(data.getAlgorithmId());
        if (algorithm.equals(BluetoothDimensionAlgorithm.BASE)) {
            getViewState().initRadarChart(getSubstanceList());
        }
    }

    public List<Substance> getSubstanceList() {

        val a15 = getData(getMaxSensResult(), 15);
        val a20 = getData(getMaxSensResult(), 20);
        val a25 = getData(getMaxSensResult(), 25);
        val a30 = getData(getMaxSensResult(), 30);
        val a40 = getData(getMaxSensResult(), 40);
        val a45 = getData(getMaxSensResult(), 45);
        val a60 = getData(getMaxSensResult(), 60);

        val a_20_30 = a30 == 0 ? -9999 : round((double) a20 / a30);
        val a_20_60 = a60 == 0 ? -9999 : round((double) a20 / a60);

        val a_15_20 = a20 == 0 ? -9999 : round((double) a15 / a20);
        val a_25_45 = a45 == 0 ? -9999 : round((double) a25 / a45);
        val a_30_60 = a60 == 0 ? -9999 : round((double) a30 / a60);
        val a_40_60 = a60 == 0 ? -9999 : round((double) a40 / a60);

        val algorithm = BluetoothDimensionAlgorithm.getByAlgorithmId(data.getAlgorithmId());

        val T = calculateT(algorithm.getMaxSignalTime(), getMaxSensResult());

        val oneSensorResultParameters = getOneSensorResultParameters(getMaxSensResult(), algorithm);
        val sensorValueAttitudeFor30 = new SensorValueAttitudeFor30(getMaxSensResult());

        val s30_60 = oneSensorResultParameters.getS30_60();

        List<Substance> substances = new ArrayList<>();

        val parametersData = SubstancesParametersData.builder()
                .II(a_40_60)
                .III(a_30_60)
                .V(a_20_60)
                .IV(a_20_30)
                .T_60(T)
                .S_30_60(s30_60)
                .S_15_30(sensorValueAttitudeFor30.calculateAndGetS15_30())
                .VI_L(a_25_45)
                .VII_L(a_15_20)
                .II_30(sensorValueAttitudeFor30.getA15_30())
                .I_30(sensorValueAttitudeFor30.getA10_20())
                .build();

//        final double II = a_40_60;
//        final double III = a_30_60;
//        final double V = a_20_60;
//        final double IV = a_20_30;
//        final int T_60 = T;
//        final Double S_30_60 = s30_60;
//        final Double S_15_30 = sensorValueAttitudeFor30.calculateAndGetS15_30();
//        final double VI_L = a_25_45;
//        final double VII_L = a_15_20;
//        final double II_30 = sensorValueAttitudeFor30.getA15_30();
//        final double I_30 = sensorValueAttitudeFor30.getA10_20();

        switch (page) {
            case SUBSTANCE_PAGE: return SubstancesUtils.getSubstances(parametersData);
            case OBJECT_PAGE: return SubstancesUtils.getObjects(parametersData);
            case BIOASSAYS: return SubstancesUtils.getBioassays(parametersData);
            default: return Collections.emptyList();
        }
    }


    private List<Integer> getMaxSensResult() {
        return data.getData();
    }
}
