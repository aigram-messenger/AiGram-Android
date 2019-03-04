package org.telegram.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.smedialink.aigram.purchases.domain.GetShopItemsUseCase;
import com.smedialink.aigram.purchases.domain.ManageShopItemUseCase;
import com.smedialink.aigram.purchases.domain.model.ShopItem;
import com.smedialink.aigram.purchases.domain.repository.ShopRepository;
import com.smedialink.responses.BotsRemoteEventsUseCase;
import com.smedialink.responses.domain.model.enums.SmartBotType;
import com.smedialink.smartpanel.view.NeuroBotInfoView;

import org.telegram.messenger.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NeuroBotInfoDialog extends Dialog implements NeuroBotInfoView.NeuroBotInfoViewDelegate {

    private NeuroBotInfoView neuroBotInfoView;
    private ManageShopItemUseCase manageShopItemUseCase;
    private GetShopItemsUseCase getShopItemsUseCase;
    private BotsRemoteEventsUseCase remoteEventsUseCase;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private int userId;

    public NeuroBotInfoDialog(Context context, int userId) {
        super(context, R.style.BottomDialogTheme);

        ShopRepository shopRepository = new ShopRepository(context);
        manageShopItemUseCase = new ManageShopItemUseCase(shopRepository);
        getShopItemsUseCase = new GetShopItemsUseCase(shopRepository);
        neuroBotInfoView = new NeuroBotInfoView(context, this);
        remoteEventsUseCase = new BotsRemoteEventsUseCase(context, userId);
        this.userId = userId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(neuroBotInfoView);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        params.windowAnimations = R.style.BottomDialogTheme;
        getWindow().setAttributes(params);
    }

    public void showNeuroBot(@NonNull ShopItem shopItem) {
        //todo загрузка данных о рейтинге и установках
        neuroBotInfoView.setShopItem(shopItem);

        compositeDisposable.clear();
        compositeDisposable.add(getShopItemsUseCase.getByType(shopItem.getSmartBotType())
                .skip(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        neuroBotInfoView::setShopItem,
                        Throwable::printStackTrace
                ));
    }

    @Override
    public void onCloseClick() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override
    public void onEnableClick() {
        ShopItem currentItem = neuroBotInfoView.getShopItem();
        if (currentItem != null) {
            manageShopItemUseCase.manage(currentItem, true);
        }
    }

    @Override
    public void onRatingChosen(SmartBotType type, int rating) {
        remoteEventsUseCase.sendBotRatingEvent(type, userId, rating);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        compositeDisposable.clear();
    }

    public void onDestroy() {
        manageShopItemUseCase.dispose();
        getShopItemsUseCase.dispose();
        remoteEventsUseCase.dispose();
    }
}
