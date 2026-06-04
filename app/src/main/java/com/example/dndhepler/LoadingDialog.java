package com.example.dndhepler;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class LoadingDialog {

    private interface QueueTask {
        void start(Loading.LoadListener listener);
    }

    private static void runNextTask(Activity activity, QueueTask[] tasks, int index) {
        if (index >= tasks.length) {
            return;
        }
        tasks[index].start(new Loading.LoadListener() {
            @Override
            public void onProgress(int current, int total, String name) {
            }

            @Override
            public void onFinish() {
                runNextTask(activity, tasks, index + 1);
            }

            @Override
            public void onError(Exception e) {
                runNextTask(activity, tasks, index + 1);
            }
        });
    }
    public static void show(Activity activity,
                            boolean loadBestiary24,
                            boolean loadBestiary14,
                            boolean loadSpells24,
                            boolean loadSpells14) {
        View view = activity.getLayoutInflater().inflate(R.layout.loading_dialog, null);
        TextView bestiary24Status = view.findViewById(R.id.statusBestiary24);
        TextView bestiary14Status = view.findViewById(R.id.statusBestiary14);
        TextView spells24Status = view.findViewById(R.id.statusSpells24);
        TextView spells14Status = view.findViewById(R.id.statusSpells14);
        if (!loadBestiary24) {
            bestiary24Status.setVisibility(View.GONE);
        }
        if (!loadBestiary14) {
            bestiary14Status.setVisibility(View.GONE);
        }
        if (!loadSpells24) {
            spells24Status.setVisibility(View.GONE);
        }
        if (!loadSpells14) {
            spells14Status.setVisibility(View.GONE);
        }
        Button buttonClose = view.findViewById(R.id.closeButton);
        AlertDialog dialog = new AlertDialog.Builder(activity).setView(view).setCancelable(false).create();
        dialog.show();
        buttonClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        ArrayList<QueueTask> taskList = new ArrayList<>();
        if (loadBestiary24) {
            taskList.add(listener -> {
                Loading.bestiary24Load(activity, new Loading.LoadListener() {
                    @Override
                    public void onProgress(int current, int total, String name) {
                        activity.runOnUiThread(() -> {
                            bestiary24Status.setText("Бестиарий 24\n" + current + "/" + total + "\n" + name);
                        });
                        listener.onProgress(current, total, name);
                    }

                    @Override
                    public void onFinish() {
                        activity.runOnUiThread(() -> {
                            bestiary24Status.setText("Бестиарий 24\nЗагрузка завершена");
                        });
                        listener.onFinish();
                    }

                    @Override
                    public void onError(Exception e) {
                        activity.runOnUiThread(() -> {
                            bestiary24Status.setText("Бестиарий 24\nОшибка: " + e.getMessage());
                        });
                        listener.onError(e);
                    }
                });
            });
        }
        if (loadBestiary14) {
            taskList.add(listener -> {
                Loading.bestiary14Load(activity, new Loading.LoadListener() {
                    @Override
                    public void onProgress(int current, int total, String name) {
                        activity.runOnUiThread(() -> {
                            bestiary14Status.setText("Бестиарий 14\n" + current + "/" + total + "\n" + name);
                        });
                        listener.onProgress(current, total, name);
                    }

                    @Override
                    public void onFinish() {
                        activity.runOnUiThread(() -> {
                            bestiary14Status.setText("Бестиарий 14\nЗагрузка завершена");
                        });
                        listener.onFinish();
                    }

                    @Override
                    public void onError(Exception e) {
                        activity.runOnUiThread(() -> {
                            bestiary14Status.setText("Бестиарий 14\nОшибка: " + e.getMessage());
                        });
                        listener.onError(e);
                    }
                });
            });
        }
        if (loadSpells24) {
           taskList.add(listener -> {
               Loading.spells24Load(activity, new Loading.LoadListener() {
                   @Override
                   public void onProgress(int current, int total, String name) {
                       activity.runOnUiThread(() -> {
                           spells24Status.setText("Заклинания 24\n" + current + "/" + total + "\n" + name);
                       });
                       listener.onProgress(current, total, name);
                   }

                   @Override
                   public void onFinish() {
                       activity.runOnUiThread(() -> {
                           spells24Status.setText("Заклинания 24\nЗагрузка завершена");
                       });
                       listener.onFinish();
                   }

                   @Override
                   public void onError(Exception e) {
                       activity.runOnUiThread(() -> {
                           spells24Status.setText("Заклинания 24\nОшибка: " + e.getMessage());
                       });
                       listener.onError(e);
                   }
               });
            });
        }
        if (loadSpells14) {
            taskList.add(listener -> {
                Loading.spells14Load(activity, new Loading.LoadListener() {
                    @Override
                    public void onProgress(int current, int total, String name) {
                        activity.runOnUiThread(() -> {
                            spells14Status.setText("Заклинания 14\n" + current + "/" + total + "\n" + name);
                        });
                        listener.onProgress(current, total, name);
                    }

                    @Override
                    public void onFinish() {
                        activity.runOnUiThread(() -> {
                            spells14Status.setText("Заклинания 14\nЗагрузка завершена");
                        });
                        listener.onFinish();
                    }

                    @Override
                    public void onError(Exception e) {
                        activity.runOnUiThread(() -> {
                            spells14Status.setText("Заклинания 14\nОшибка: " + e.getMessage());
                        });
                        listener.onError(e);
                    }
                });
            });
        }
        if (!taskList.isEmpty()) {
            QueueTask[] tasks = taskList.toArray(new QueueTask[0]);
            runNextTask(activity, tasks, 0);
        }
    }
}
