package albastro.decision.albastro_wagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display extends AppCompatActivity implements View.OnClickListener {

    TextView txtEmpName,txtEmpType,txtHoursRendered,txtWage;
    Button btnBack;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        txtEmpName = findViewById(R.id.txtEmployeeName);
        txtEmpType = findViewById(R.id.txtEmployeeType);
        txtHoursRendered = findViewById(R.id.txtHours);

        txtWage = findViewById(R.id.txtTotalWage);


        btnBack = findViewById(R.id.btnBack);

        Intent i = getIntent();

        String EmployeeType =i.getStringExtra("type");
        String EmployeeName = i.getStringExtra("empName");
        Double EmployeeHours = Double.parseDouble(i.getStringExtra("hours"));

        txtEmpName.setText("Employee Name: " +EmployeeName);
        txtEmpType.setText("Employee Type: " + String.valueOf(EmployeeType));
        txtHoursRendered.setText("Hours Rendered: "+ String.valueOf(EmployeeHours));

        calcWage(EmployeeType,EmployeeHours,txtWage);

    }

    @SuppressLint("SetTextI18n")
    public void calcWage(String employeeType,Double employeeHours, TextView txtWage) {
        double totalWage = 0.0;


        if (employeeHours > 8.0) {
            if (employeeType.equals("Full-time")) {
                totalWage = 800 + (115 * (employeeHours - 8.0));
                txtWage.setText("Total wage W/ OVERTIME: ₱" + String.valueOf(totalWage));
            }
            else if (employeeType.equals("Part-time")) {
                totalWage = 600 + (90 * (employeeHours - 8.0));
                txtWage.setText("Total wage W/ OVERTIME: ₱" + String.valueOf(totalWage));
            }
            if (employeeType.equals("Probationary")) {
                totalWage = 720 + (100 * (employeeHours - 8.0));
                txtWage.setText("Total wage W/ OVERTIME: ₱" + String.valueOf(totalWage));

            }
        }
        else {
            if (employeeType.equals("Full-time")) {
                totalWage = employeeHours * 100;
                txtWage.setText("Total Wage: ₱" + String.valueOf(totalWage));
            }
            if (employeeType.equals("Part-time")) {
                totalWage = employeeHours * 75;
                txtWage.setText("Total Wage: ₱" + String.valueOf(totalWage));
            }
            if (employeeType.equals("Probationary")) {
                totalWage = employeeHours * 90;
                txtWage.setText("Total Wage: ₱" + String.valueOf(totalWage));
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBack){
            startActivity(new Intent(Display.this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

    }
}