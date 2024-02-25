package com.murik.lite.presentation.presenter.substances;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.murik.lite.dto.MeasureDataParcelable;
import com.murik.lite.enums.BluetoothDimensionAlgorithm;
import com.murik.lite.model.SensorValueAttitudeFor30;
import com.murik.lite.model.substances.Substance;
import com.murik.lite.model.substances.ValueInRange;
import com.murik.lite.presentation.view.substances.SubstancesView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Setter;
import lombok.val;

import static com.murik.lite.service.Impl.BaseMeasureService.calculateT;
import static com.murik.lite.service.Impl.BaseMeasureService.getOneSensorResultParameters;
import static com.murik.lite.service.Impl.BaseMeasureService.round;
import static com.murik.lite.utils.ListUtils.getData;

@InjectViewState
@Setter
public class SubstancesPresenter extends MvpPresenter<SubstancesView> {
    private MeasureDataParcelable data;

    public void setData(MeasureDataParcelable data) {
        this.data = data;
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
        val a70 = getData(getMaxSensResult(), 70);

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

        val II = a_40_60;
        val III = a_30_60;
        val V = a_20_60;
        val IV = a_20_30;
        val T_60 = T;
        val S_30_60 = s30_60;
        val S_15_30 = sensorValueAttitudeFor30.calculateAndGetS15_30();
        val VI_L = a_25_45;
        val VII_L = a_15_20;
        val II_30 = sensorValueAttitudeFor30.getA15_30();
        val I_30 = sensorValueAttitudeFor30.getA10_20();


        Collections.addAll(substances,
                new Substance("Вода",
                        new ValueInRange(II, 0.64, 0.68),
                        new ValueInRange(III, 0.45, 0.48),
                        new ValueInRange(V, 0.28, 0.27),
                        new ValueInRange(IV, 0.48, 0.53),
                        new ValueInRange(T_60, 52, 58),
                        new ValueInRange(S_30_60, 0.18, 0.21),
                        new ValueInRange(S_15_30, 0.14, 0.16),
                        new ValueInRange(VI_L, 0.47, 0.51),
                        new ValueInRange(VII_L, 0.62, 0.65),
                        new ValueInRange(II_30, 0.29, 0.33),
                        new ValueInRange(I_30, 0.26, 0.28)
                ),
                new Substance("Бутанол-1",
                        new ValueInRange(II, 0.64, 0.65),
                        new ValueInRange(III, 0.44, 0.46),
                        new ValueInRange(V, 0.23, 0.26),
                        new ValueInRange(IV, 0.53, 0.58),
                        new ValueInRange(T_60, 54, 56),
                        new ValueInRange(S_30_60, 0.18, 0.2),
                        new ValueInRange(S_15_30, 0.11, 0.15),
                        new ValueInRange(VI_L, 0.45, 0.53),
                        new ValueInRange(VII_L, 0.59, 0.62),
                        new ValueInRange(II_30, 0.26, 0.35),
                        new ValueInRange(I_30, 0.18, 0.25)
                ),
                new Substance("Бутанол-2",
                        new ValueInRange(II, 0.62, 0.66),
                        new ValueInRange(III, 0.38, 0.50),
                        new ValueInRange(V, 0.20, 0.30),
                        new ValueInRange(IV, 0.53, 0.59),
                        new ValueInRange(T_60, 50, 58),
                        new ValueInRange(S_30_60, 0.17, 0.23),
                        new ValueInRange(S_15_30, 0.16, 0.18),
                        new ValueInRange(VI_L, 0.40, 0.51),
                        new ValueInRange(VII_L, 0.63, 0.682),
                        new ValueInRange(II_30, 0.33, 0.36),
                        new ValueInRange(I_30, 0.29, 0.33)
                ),
                new Substance("Этанол",
                        new ValueInRange(II, 0.64, 0.66),
                        new ValueInRange(III, 0.43, 0.48),
                        new ValueInRange(V, 0.23, 0.27),
                        new ValueInRange(IV, 0.54, 0.66),
                        new ValueInRange(T_60, 54, 58),
                        new ValueInRange(S_30_60, 0.18, 0.25),
                        new ValueInRange(S_15_30, 0.15, 0.22),
                        new ValueInRange(VI_L, 0.48, 0.49),
                        new ValueInRange(VII_L, 0.60, 0.63),
                        new ValueInRange(II_30, 0.33, 0.46),
                        new ValueInRange(I_30, 0.26, 0.28)
                ),
                new Substance("Пентанол-2",
                        new ValueInRange(II, 0.50, 0.63),
                        new ValueInRange(III, 0.31, 0.48),
                        new ValueInRange(V, 0.31, 0.40),
                        new ValueInRange(IV, 0.75, 0.83),
                        new ValueInRange(T_60, 48, 54),
                        new ValueInRange(S_30_60, 0.27, 0.30),
                        new ValueInRange(S_15_30, 0.30, 0.36),
                        new ValueInRange(VI_L, 0.50, 0.56),
                        new ValueInRange(VII_L, 0.80, 1.20),
                        new ValueInRange(II_30, 0.5, 0.67),
                        new ValueInRange(I_30, 0.5, 0.9)
                ),
                new Substance("Циклогексанон",
                        new ValueInRange(II, 0.69, 0.72),
                        new ValueInRange(III, 0.5, 0.52),
                        new ValueInRange(V, 0.28, 0.32),
                        new ValueInRange(IV, 0.58, 0.6),
                        new ValueInRange(T_60, 60, 62),
                        new ValueInRange(S_30_60, 0.2, 0.22),
                        new ValueInRange(S_15_30, 0.17, 0.18),
                        new ValueInRange(VI_L, 0.5, 0.53),
                        new ValueInRange(VII_L, 0.63, 0.66),
                        new ValueInRange(II_30, 0.34, 0.37),
                        new ValueInRange(I_30, 0.26, 0.31)
                ),
                new Substance("Ацетон",
                        new ValueInRange(II, 0.66, 0.84),
                        new ValueInRange(III, 0.5, 0.63),
                        new ValueInRange(V, 0.29, 0.36),
                        new ValueInRange(IV, 0.55, 0.64),
                        new ValueInRange(T_60, 60, 68),
                        new ValueInRange(S_30_60, 0.2, 0.23),
                        new ValueInRange(S_15_30, 0.13, 0.20),
                        new ValueInRange(VI_L, 0.51, 0.57),
                        new ValueInRange(VII_L, 0.55, 0.70),
                        new ValueInRange(II_30, 0.25, 0.37),
                        new ValueInRange(I_30, 0.2, 0.39)
                ),
                new Substance("Ацетальдегид",
                        new ValueInRange(II, 0.6, 0.7),
                        new ValueInRange(III, 0.42, 0.51),
                        new ValueInRange(V, 0.21, 0.3),
                        new ValueInRange(IV, 0.51, 0.59),
                        new ValueInRange(T_60, 52, 60),
                        new ValueInRange(S_30_60, 0.17, 0.22),
                        new ValueInRange(S_15_30, 0.12, 0.18),
                        new ValueInRange(VI_L, 0.43, 0.51),
                        new ValueInRange(VII_L, 0.58, 0.66),
                        new ValueInRange(II_30, 0.27, 0.35),
                        new ValueInRange(I_30, 0.18, 0.33)
                ),
                new Substance("Метиламин",
                        new ValueInRange(II, 0.51, 0.58),
                        new ValueInRange(III, 0.31, 0.38),
                        new ValueInRange(V, 0.16, 0.2),
                        new ValueInRange(IV, 0.51, 0.55),
                        new ValueInRange(T_60, 40, 48),
                        new ValueInRange(S_30_60, 0.15, 0.17),
                        new ValueInRange(S_15_30, 0.14, 0.19),
                        new ValueInRange(VI_L, 0.36, 0.4),
                        new ValueInRange(VII_L, 0.62, 0.69),
                        new ValueInRange(II_30, 0.3, 0.35),
                        new ValueInRange(I_30, 0.27, 0.37)
                ),
                new Substance("Диэтиламин",
                        new ValueInRange(II, 0.53, 0.7),
                        new ValueInRange(III, 0.32, 0.5),
                        new ValueInRange(V, 0.13, 0.3),
                        new ValueInRange(IV, 0.55, 0.69),
                        new ValueInRange(T_60, 42, 60),
                        new ValueInRange(S_30_60, 0.14, 0.27),
                        new ValueInRange(S_15_30, 0.1, 0.15),
                        new ValueInRange(VI_L, 0.49, 0.64),
                        new ValueInRange(VII_L, 0.62, 0.73),
                        new ValueInRange(II_30, 0.32, 0.48),
                        new ValueInRange(I_30, 0.26, 0.41)
                ),
                new Substance("Муравьиная кислота",
                        new ValueInRange(II, 0.6, 0.65),
                        new ValueInRange(III, 0.4, 0.45),
                        new ValueInRange(V, 0.2, 0.24),
                        new ValueInRange(IV, 0.51, 0.54),
                        new ValueInRange(T_60, 48, 54),
                        new ValueInRange(S_30_60, 0.17, 0.19),
                        new ValueInRange(S_15_30, 0.13, 0.15),
                        new ValueInRange(VI_L, 0.41, 0.47),
                        new ValueInRange(VII_L, 0.57, 0.6),
                        new ValueInRange(II_30, 0.26, 0.29),
                        new ValueInRange(I_30, 0.21, 0.26)
                ),
                new Substance("Уксусная кислота",
                        new ValueInRange(II, 0.6, 0.62),
                        new ValueInRange(III, 0.4, 0.42),
                        new ValueInRange(V, 0.22, 0.23),
                        new ValueInRange(IV, 0.53, 0.55),
                        new ValueInRange(T_60, 50, 52),
                        new ValueInRange(S_30_60, 0.18, 0.19),
                        new ValueInRange(S_15_30, 0.15, 0.16),
                        new ValueInRange(VI_L, 0.45, 0.47),
                        new ValueInRange(VII_L, 0.6, 0.63),
                        new ValueInRange(II_30, 0.29, 0.32),
                        new ValueInRange(I_30, 0.25, 0.28)
                ),
                new Substance("Молочная кислота",
                        new ValueInRange(II, 0.65, 0.7),
                        new ValueInRange(III, 0.46, 0.52),
                        new ValueInRange(V, 0.26, 0.35),
                        new ValueInRange(IV, 0.56, 0.67),
                        new ValueInRange(T_60, 56, 60),
                        new ValueInRange(S_30_60, 0.19, 0.23),
                        new ValueInRange(S_15_30, 0.14, 0.19),
                        new ValueInRange(VI_L, 0.47, 0.52),
                        new ValueInRange(VII_L, 0.6, 0.67),
                        new ValueInRange(II_30, 0.3, 0.38),
                        new ValueInRange(I_30, 0.29, 0.38)
                ),
                new Substance("Масляная кислота",
                        new ValueInRange(II, 0.57, 0.6),
                        new ValueInRange(III, 0.37, 0.43),
                        new ValueInRange(V, 0.2, 0.23),
                        new ValueInRange(IV, 0.42, 0.54),
                        new ValueInRange(T_60, 36, 48),
                        new ValueInRange(S_30_60, 0.13, 0.18),
                        new ValueInRange(S_15_30, 0.11, 0.13),
                        new ValueInRange(VI_L, 0.31, 0.43),
                        new ValueInRange(VII_L, 0.57, 0.6),
                        new ValueInRange(II_30, 0.25, 0.36),
                        new ValueInRange(I_30, 0, 0.2)
                ),
                new Substance("Пентанол-1",
                        new ValueInRange(II, 0.58, 0.63),
                        new ValueInRange(III, 0.35, 0.39),
                        new ValueInRange(V, 0.15, 0.18),
                        new ValueInRange(IV, 0.4, 0.44),
                        new ValueInRange(T_60, 46, 48),
                        new ValueInRange(S_30_60, 0.14, 0.16),
                        new ValueInRange(S_15_30, 0.08, 0.12),
                        new ValueInRange(VI_L, 0.39, 0.42),
                        new ValueInRange(VII_L, 0.50, 0.80),
                        new ValueInRange(II_30, 0.16, 0.22),
                        new ValueInRange(I_30, -0.11, 0.12)
                ),
                new Substance("Метилэтилкетон",
                        new ValueInRange(II, 0.63, 0.76),
                        new ValueInRange(III, 0.46, 0.56),
                        new ValueInRange(V, 0.26, 0.33),
                        new ValueInRange(IV, 0.56, 0.6),
                        new ValueInRange(T_60, 56, 66),
                        new ValueInRange(S_30_60, 0.2, 0.24),
                        new ValueInRange(S_15_30, 0.17, 0.18),
                        new ValueInRange(VI_L, 0.49, 0.54),
                        new ValueInRange(VII_L, 0.65, 0.68),
                        new ValueInRange(II_30, 0.33, 0.36),
                        new ValueInRange(I_30, 0.30, 0.33)
                ),
                new Substance("Хлороформ",
                        new ValueInRange(II, 0.64, 0.66),
                        new ValueInRange(III, 0.45, 0.47),
                        new ValueInRange(V, 0.25, 0.27),
                        new ValueInRange(IV, 0.5, 0.58),
                        new ValueInRange(T_60, 55, 57),
                        new ValueInRange(S_30_60, 0.19, 0.21),
                        new ValueInRange(S_15_30, 0.16, 0.17),
                        new ValueInRange(VI_L, 0.40, 0.48),
                        new ValueInRange(VII_L, 0.62, 0.63),
                        new ValueInRange(II_30, 0.32, 0.34),
                        new ValueInRange(I_30, 0.30, 0.40)
                ),
                new Substance("Пропионовая кислота",
                        new ValueInRange(II, 0.58, 0.62),
                        new ValueInRange(III, 0.41, 0.51),
                        new ValueInRange(V, 0.22, 0.28),
                        new ValueInRange(IV, 0.53, 0.55),
                        new ValueInRange(T_60, 52, 60),
                        new ValueInRange(S_30_60, 0.18, 0.20),
                        new ValueInRange(S_15_30, 0.14, 0.16),
                        new ValueInRange(VI_L, 0.38, 0.44),
                        new ValueInRange(VII_L, 0.62, 0.63),
                        new ValueInRange(II_30, 0.31, 0.32),
                        new ValueInRange(I_30, 0.25, 0.28)
                ),
                new Substance("Уксус 1М",
                        new ValueInRange(II, 0.66, 0.67),
                        new ValueInRange(III, 0.45, 0.48),
                        new ValueInRange(V, 0.24, 0.25),
                        new ValueInRange(IV, 0.52, 0.53),
                        new ValueInRange(T_60, 56, 58),
                        new ValueInRange(S_30_60, 0.19, 0.20),
                        new ValueInRange(S_15_30, 0.15, 0.16),
                        new ValueInRange(VI_L, 0.45, 0.47),
                        new ValueInRange(VII_L, 0.6, 0.67),
                        new ValueInRange(II_30, 0.30, 0.31),
                        new ValueInRange(I_30, 0.27, 0.29)
                ),
                new Substance("Аммиак, 10 %",
                        new ValueInRange(II, 0.68, 0.70),
                        new ValueInRange(III, 0.47, 0.51),
                        new ValueInRange(V, 0.26, 0.30),
                        new ValueInRange(IV, 0.56, 0.59),
                        new ValueInRange(T_60, 56, 62),
                        new ValueInRange(S_30_60, 0.2, 0.22),
                        new ValueInRange(S_15_30, 0.15, 0.19),
                        new ValueInRange(VI_L, 0.47, 0.52),
                        new ValueInRange(VII_L, 0.63, 0.66),
                        new ValueInRange(II_30, 0.33, 0.37),
                        new ValueInRange(I_30, 0.27, 0.34)
                ),
                new Substance("Хлороформ толуол (3:1)",
                        new ValueInRange(II, 0.58, 0.64),
                        new ValueInRange(III, 0.36, 0.44),
                        new ValueInRange(V, 0.15, 0.24),
                        new ValueInRange(IV, 0.42, 0.53),
                        new ValueInRange(T_60, 46, 54),
                        new ValueInRange(S_30_60, 0.14, 0.19),
                        new ValueInRange(S_15_30, 0.08, 0.16),
                        new ValueInRange(VI_L, 0.35, 0.45),
                        new ValueInRange(VII_L, 0.4, 0.62),
                        new ValueInRange(II_30, 0.14, 0.31),
                        new ValueInRange(I_30, 0.06, 0.27)
                ),
                new Substance("1,4-Диоксан",
                        new ValueInRange(II, 0.62, 0.63),
                        new ValueInRange(III, 0.42, 0.43),
                        new ValueInRange(V, 0.23, 0.24),
                        new ValueInRange(IV, 0.53, 0.57),
                        new ValueInRange(T_60, 51, 52),
                        new ValueInRange(S_30_60, 0.18, 0.19),
                        new ValueInRange(S_15_30, 0.15, 0.17),
                        new ValueInRange(VI_L, 0.45, 0.46),
                        new ValueInRange(VII_L, 0.63, 0.64),
                        new ValueInRange(II_30, 0.32, 0.33),
                        new ValueInRange(I_30, 0.25, 0.3)
                ),
                new Substance("Метоксиуксусная кислота",
                        new ValueInRange(II, 0.66, 0.68),
                        new ValueInRange(III, 0.45, 0.47),
                        new ValueInRange(V, 0.23, 0.25),
                        new ValueInRange(IV, 0.54, 0.56),
                        new ValueInRange(T_60, 57, 59),
                        new ValueInRange(S_30_60, 0.19, 0.20),
                        new ValueInRange(S_15_30, 0.17, 0.18),
                        new ValueInRange(VI_L, 0.47, 0.48),
                        new ValueInRange(VII_L, 0.66, 0.67),
                        new ValueInRange(II_30, 0.30, 0.33),
                        new ValueInRange(I_30, 0.29, 0.3)
                ),
                new Substance("Циклопентиламин",
                        new ValueInRange(II, 0.51, 0.52),
                        new ValueInRange(III, 0.31, 0.33),
                        new ValueInRange(V, 0.12, 0.15),
                        new ValueInRange(IV, 0.38, 0.47),
                        new ValueInRange(T_60, 41, 43),
                        new ValueInRange(S_30_60, 0.12, 0.15),
                        new ValueInRange(S_15_30, 0.04, 0.12),
                        new ValueInRange(VI_L, 0.33, 0.37),
                        new ValueInRange(VII_L, 0.34, 0.54),
                        new ValueInRange(II_30, 0.10, 0.23),
                        new ValueInRange(I_30, 0.03, 0.24)
                ),
                new Substance("Моноэтаноламин",
                        new ValueInRange(II, 0.63, 0.68),
                        new ValueInRange(III, 0.46, 0.49),
                        new ValueInRange(V, 0.27, 0.3),
                        new ValueInRange(IV, 0.59, 0.61),
                        new ValueInRange(T_60, 56, 60),
                        new ValueInRange(S_30_60, 0.19, 0.2),
                        new ValueInRange(S_15_30, 0.15, 0.17),
                        new ValueInRange(VI_L, 0.44, 0.49),
                        new ValueInRange(VII_L, 0.6, 0.61),
                        new ValueInRange(II_30, 0.31, 0.32),
                        new ValueInRange(I_30, 0.27, 0.31)
                ),
                new Substance("Диэтаноламин",
                        new ValueInRange(II, 0.63, 0.66),
                        new ValueInRange(III, 0.43, 0.45),
                        new ValueInRange(V, 0.24, 0.25),
                        new ValueInRange(IV, 0.54, 0.56),
                        new ValueInRange(T_60, 54, 56),
                        new ValueInRange(S_30_60, 0.18, 0.19),
                        new ValueInRange(S_15_30, 0.13, 0.14),
                        new ValueInRange(VI_L, 0.46, 0.47),
                        new ValueInRange(VII_L, 0.58, 0.62),
                        new ValueInRange(II_30, 0.29, 0.31),
                        new ValueInRange(I_30, 0.22, 0.23)
                ),
                new Substance("Метоксиэтанамин (4-метилморфолин)",
                        new ValueInRange(II, 0.50, 0.51),
                        new ValueInRange(III, 0.26, 0.3),
                        new ValueInRange(V, 0.06, 0.14),
                        new ValueInRange(IV, 0.22, 0.45),
                        new ValueInRange(T_60, 38, 40),
                        new ValueInRange(S_30_60, 0.11, 0.13),
                        new ValueInRange(S_15_30, 0.09, 0.12),
                        new ValueInRange(VI_L, 0.29, 0.34),
                        new ValueInRange(VII_L, 0.5, 0.51),
                        new ValueInRange(II_30, 0.12, 0.2),
                        new ValueInRange(I_30, -0.33, 0.11)
                ),
                new Substance("Морфолин",
                        new ValueInRange(II, -0.10, 0.29),
                        new ValueInRange(III, -0.4, 0.03),
                        new ValueInRange(V, -0.4, -0.1),
                        new ValueInRange(IV, -2.20, 1),
                        new ValueInRange(T_60, 16, 28),
                        new ValueInRange(S_30_60, 0.12, 0.42),
                        new ValueInRange(S_15_30, 0.27, 0.47),
                        new ValueInRange(VI_L, -2.9, -0.1),
                        new ValueInRange(VII_L, 0.85, 1.2),
                        new ValueInRange(II_30, -7.3, 0.79),
                        new ValueInRange(I_30, -0.47, 0.76)
                ),
                new Substance("Аминопропиламидозол",
                        new ValueInRange(II, 0.64, 0.65),
                        new ValueInRange(III, 0.43, 0.46),
                        new ValueInRange(V, 0.24, 0.25),
                        new ValueInRange(IV, 0.56, 0.58),
                        new ValueInRange(T_60, 52, 56),
                        new ValueInRange(S_30_60, 0.19, 0.2),
                        new ValueInRange(S_15_30, 0.15, 0.17),
                        new ValueInRange(VI_L, 0.44, 0.49),
                        new ValueInRange(VII_L, 0.6, 0.61),
                        new ValueInRange(II_30, 0.32, 0.33),
                        new ValueInRange(I_30, 0.27, 0.31)
                ),
                new Substance("МетилОэтиламин",
                        new ValueInRange(II, 0.47, 0.5),
                        new ValueInRange(III, 0.24, 0.27),
                        new ValueInRange(V, 0.05, 0.08),
                        new ValueInRange(IV, 0.2, 0.34),
                        new ValueInRange(T_60, 38, 40),
                        new ValueInRange(S_30_60, 0.1, 0.11),
                        new ValueInRange(S_15_30, 0.08, 0.2),
                        new ValueInRange(VI_L, 0.24, 0.4),
                        new ValueInRange(VII_L, 0.47, 0.66),
                        new ValueInRange(II_30, 0.15, 0.36),
                        new ValueInRange(I_30, 0.2, 0.48)
                )
            );

        return substances;
    }


    private List<Integer> getMaxSensResult() {
        return data.getData();
    }
}
