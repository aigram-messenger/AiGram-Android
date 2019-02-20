package org.telegram.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.smedialink.aigram.purchases.domain.GetShopItemsUseCase;
import com.smedialink.aigram.purchases.domain.ManageShopItemUseCase;
import com.smedialink.aigram.purchases.domain.model.ShopItem;
import com.smedialink.aigram.purchases.domain.repository.ShopRepository;
import com.smedialink.responses.domain.model.NeuroBotType;
import com.smedialink.smartpanel.view.NeuroBotPopupView;

import org.telegram.messenger.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NeuroBotPopupDialog extends Dialog implements NeuroBotPopupView.NeuroBotPopupViewDelegate {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ManageShopItemUseCase manageShopItemUseCase;
    private GetShopItemsUseCase getShopItemsUseCase;

    private NeuroBotPopupView neuroBotPopupView;
    private ShopItem currentShopItem;

    private NeuroBotType currentBotType;

    private Listener listener;

    public interface Listener {
        void onInfoClick(ShopItem shopItem);

        void onShareClick();

        void onDisableClick();
    }

    public NeuroBotPopupDialog(Context context) {
        super(context, R.style.BottomDialogTheme);

        ShopRepository shopRepository = new ShopRepository(context);
        manageShopItemUseCase = new ManageShopItemUseCase(shopRepository);
        getShopItemsUseCase = new GetShopItemsUseCase(shopRepository);
        neuroBotPopupView = new NeuroBotPopupView(context, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(neuroBotPopupView);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        params.windowAnimations = R.style.BottomDialogTheme;
        getWindow().setAttributes(params);
    }

    public void setCurrentBot(NeuroBotType botType) {
        currentBotType = botType;

        compositeDisposable.clear();
        compositeDisposable.add(getShopItemsUseCase.getByType(currentBotType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shopItem -> currentShopItem = shopItem, Throwable::printStackTrace));
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        compositeDisposable.clear();
    }

    @Override
    public void onInfoClick() {
        if (listener != null && currentShopItem != null) {
            listener.onInfoClick(currentShopItem);
        }
    }

    @Override
    public void onShareClick() {
        if (listener != null) {
            listener.onShareClick();
        }
    }

    @Override
    public void onDisableClick() {
        if (currentShopItem != null) {
            manageShopItemUseCase.manage(currentShopItem, true);
        }
        if (currentBotType != null) {
            listener.onDisableClick();
        }
    }

    public void onDestroy() {
        manageShopItemUseCase.dispose();
        getShopItemsUseCase.dispose();
    }
}
