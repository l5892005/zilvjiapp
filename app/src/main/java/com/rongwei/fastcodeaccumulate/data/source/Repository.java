package com.rongwei.fastcodeaccumulate.data.source;



import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.bean.WxLoginBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.data.param.LoginParam;
import com.rongwei.fastcodeaccumulate.data.source.local.LocalDataSource;
import com.rongwei.fastcodeaccumulate.data.source.remote.RemoteDataSource;

import java.io.File;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * M层
 */
public class Repository implements DataSource {
    private static Repository INSTANCE = null;
    private final RemoteDataSource mRemoteDataSource;
    private final LocalDataSource mLocalDataSource;

    private Repository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static Repository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            synchronized (DataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Repository(remoteDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * 封装线程调度
     */
    public <T> ObservableTransformer<T, T> initNetworkThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    @Override
    public Observable<BaseResultWrapper<FastCodeBean>> getAllData() {
        return mRemoteDataSource.getAllData().compose(this.<BaseResultWrapper<FastCodeBean>>initNetworkThread());
    }



    @Override
    public Observable<BaseResultWrapper<String>> inserFastCode(InserFastCodeBean inserFastCodeBean) {
        return mRemoteDataSource.inserFastCode(inserFastCodeBean).compose(this.<BaseResultWrapper<String>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<MemoBean>> getMemoData(String userId) {
        return mRemoteDataSource.getMemoData(userId).compose(this.<BaseResultWrapper<MemoBean>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<UserCardsBean>> getCardData(String userId) {
        return mRemoteDataSource.getCardData(userId).compose(this.<BaseResultWrapper<UserCardsBean>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<UserCardsToDayBean>> getCardDataToDay(String userId) {
        return mRemoteDataSource.getCardDataToDay(userId).compose(this.<BaseResultWrapper<UserCardsToDayBean>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<String>> setCardTodayData(String userId, int order, int isCard) {
        return mRemoteDataSource.setCardTodayData(userId,order,isCard).compose(this.<BaseResultWrapper<String>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<NoteCatalogBean>> getNoteCatalog(int uid) {
        return mRemoteDataSource.getNoteCatalog(uid).compose(this.<BaseResultWrapper<NoteCatalogBean>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<PersionNoteListBean>> getNoteListCatalog(int uid, int nid) {
        return mRemoteDataSource.getNoteListCatalog(uid,nid).compose(this.<BaseResultWrapper<PersionNoteListBean>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<UserBean>> setLogin(String account, String pwd) {
        return mRemoteDataSource.setLogin(account,pwd).compose(this.<BaseResultWrapper<UserBean>>initNetworkThread());
    }
  /*

    @Override
    public Observable<BaseResultWrapper<WeChatOrderStatusBean>> getWeChatOrderStatus(Map<String, String> params) {
        return mRemoteDataSource.getWeChatOrderStatus(params).compose(this.<BaseResultWrapper<WeChatOrderStatusBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<OrderPayResultBean>> getOrderPayResult(int orderId) {
        return mRemoteDataSource.getOrderPayResult(orderId).compose(this.<BaseResultWrapper<OrderPayResultBean>>initNetworkThread());
    }*/

}
