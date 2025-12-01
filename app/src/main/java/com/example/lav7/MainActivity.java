package com.example.lav7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 100;
    private Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScan);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        }

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRScanner();
            }
        });
    }

    private void startQRScanner() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Наведите камеру на QR код");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);

        // Запуск сканера
        scanLauncher.launch(options);
    }

    // Обработчик результата сканирования
    private final androidx.activity.result.ActivityResultLauncher<ScanOptions> scanLauncher =
            registerForActivityResult(new ScanContract(), result -> {
                if (result.getContents() != null) {
                    String scannedText = result.getContents().toLowerCase().trim();
                    handleScannedText(scannedText);
                }
            });

    private void handleScannedText(String text) {
        Intent intent;

        switch (text) {
            case "дом":
                intent = new Intent(MainActivity.this, HouseActivity.class);
                startActivity(intent);
                break;

            case "машина":
                intent = new Intent(MainActivity.this, CarActivity.class);
                startActivity(intent);
                break;

            case "дерево":
                intent = new Intent(MainActivity.this, TreeActivity.class);
                startActivity(intent);
                break;

            default:
                Toast.makeText(this, "Неизвестный QR код: " + text, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Разрешение на камеру получено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Разрешение на камеру отклонено", Toast.LENGTH_SHORT).show();
            }
        }
    }
}