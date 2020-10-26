package io.flutter.plugins.firebase_mlkit_language;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateRemoteModel;

import io.flutter.plugin.common.MethodChannel;

class DeleteModel implements ModelAgent {

    static final DeleteModel instance = new DeleteModel();

    private DeleteModel() {
    }

    @Override
    public void handleEvent(String modelName, final MethodChannel.Result result) {
        FirebaseTranslateRemoteModel model = new FirebaseTranslateRemoteModel.Builder(
                FirebaseTranslateLanguage.languageForLanguageCode(modelName)).build();
        FirebaseModelManager.getInstance().deleteDownloadedModel(model)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        result.success("Deleted");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        result.error("deleteError", e.getLocalizedMessage(), null);
                    }
                });
    }
}
