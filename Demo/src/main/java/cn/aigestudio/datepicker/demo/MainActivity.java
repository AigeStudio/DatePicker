/*
 Copyright 2014-2015 AigeStudio(https://github.com/AigeStudio)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package cn.aigestudio.datepicker.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import cn.aigestudio.datepicker.interfaces.OnDateSelected;
import cn.aigestudio.datepicker.utils.LogUtil;
import cn.aigestudio.datepicker.views.DatePicker;

/**
 * Demo应用的主Activity
 * <p/>
 * The main activity of demo
 *
 * @author AigeStudio https://github.com/AigeStudio
 * @version 1.0.0 beta
 * @since 2015/3/26
 */
public class MainActivity extends Activity {
    private DatePicker mDatePicker;
    private Button btnPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mDatePicker = (DatePicker) findViewById(R.id.main_dp);
        mDatePicker.setOnDateSelected(new OnDateSelected() {
            @Override
            public void selected(List<String> date) {
                for (String s : date) {
                    LogUtil.v(s);
                }
            }
        });

        btnPick = (Button) findViewById(R.id.main_btn);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                dialog.show();

                DatePicker datePicker = new DatePicker(MainActivity.this);
                datePicker.setOnDateSelected(new OnDateSelected() {
                    @Override
                    public void selected(List<String> date) {
                        StringBuilder sb = new StringBuilder();
                        for (String s : date) {
                            sb.append(s).append("\n");
                        }
                        Toast.makeText(MainActivity.this, sb.toString(),
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                        .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setContentView(datePicker, params);
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });
    }
}
