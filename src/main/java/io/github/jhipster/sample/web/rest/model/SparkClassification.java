package io.github.jhipster.sample.web.rest.model;

import org.apache.spark.ml.Model;
import org.apache.spark.ml.classification.*;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SparkClassification {

    public Model lr(JSONObject paramPair, Dataset dataSet, String savePath) throws IOException, JSONException {
        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(paramPair.getInt("maxIter"))
                .setRegParam(paramPair.getDouble("regParam"))
                .setElasticNetParam(paramPair.getDouble("elasticNetParam"))
                .setStandardization(paramPair.getBoolean("standardization"));
        LogisticRegressionModel model_lr= lr.fit(dataSet);
        model_lr.save(savePath);
        return model_lr;
    }


    public Model dt(JSONObject paramPair, Dataset<Row> dataSet, String savePath) throws IOException, JSONException {
        DecisionTreeClassifier dt = new DecisionTreeClassifier();
        DecisionTreeClassificationModel model_dt = dt.fit(dataSet);
        model_dt.save(savePath);
        return model_dt;
    }

    public Model rf(JSONObject paramPair, Dataset<Row> dataSet, String savePath) throws IOException, JSONException {
        RandomForestClassifier rf = new RandomForestClassifier();
        RandomForestClassificationModel model_rf = rf.fit(dataSet);
        model_rf.save(savePath);
        return model_rf;
    }

    public Model gbt(JSONObject paramPair, Dataset<Row> dataSet, String savePath) throws IOException, JSONException {
        GBTClassifier gbt = new GBTClassifier();
        GBTClassificationModel model_gbt = gbt.fit(dataSet);
        model_gbt.save(savePath);
        return model_gbt;
    }

    public Model mutPerception(JSONObject paramPair, Dataset<Row> dataSet, String savePath) throws IOException, JSONException {
        MultilayerPerceptronClassifier mutPer = new MultilayerPerceptronClassifier();
        MultilayerPerceptronClassificationModel model_mutPer = mutPer.fit(dataSet);
        model_mutPer.save(savePath);
        return model_mutPer;
    }

    public Model nb(JSONObject paramPair, Dataset<Row> dataSet, String savePath) throws IOException, JSONException {
        NaiveBayes nb = new NaiveBayes();
        NaiveBayesModel model_nb = nb.fit(dataSet);
        model_nb.save(savePath);
        return model_nb;
    }

}