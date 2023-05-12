package com.example.tipcalculatormodification;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15);
    private BigDecimal totalAmount = new BigDecimal(0);


    @FXML
    private Label amountLabel;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Label tipLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        tipPercentageSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldValue, Number newValue) -> {
            tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
            tipPercentageLabel.setText(percent.format(tipPercentage));
            resultValues();
        });

        amountTextField.textProperty().addListener((args, oldValue, newValue) -> {
            try {
                totalAmount = new BigDecimal(newValue);
            } catch (Exception e) {
                amountTextField.setText("Enter number:");
            }
            resultValues();
        });
    }


    public void resultValues() {
        if (totalAmount.doubleValue() >= 0) {
            BigDecimal tip = totalAmount.multiply(tipPercentage);
            BigDecimal total = totalAmount.add(tip);
            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } else {
            tipTextField.setText("0$");
            tipTextField.setText("0$");
        }
    }
}