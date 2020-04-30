package com.rongwei.fastcodeaccumulate.data.source;



import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.bean.VersionBean;
import com.rongwei.fastcodeaccumulate.data.bean.VideoBean;
import com.rongwei.fastcodeaccumulate.data.bean.WxLoginBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.data.param.LoginParam;
import com.rongwei.fastcodeaccumulate.data.source.local.LocalDataSource;
import com.rongwei.fastcodeaccumulate.data.source.remote.RemoteDataSource;

import org.jetbrains.annotations.NotNull;

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

    public Observable<BaseResultWrapper<CardBean>> getCardData(String userId) {
        return mRemoteDataSource.getCardData(userId).compose(this.<BaseResultWrapper<CardBean>>initNetworkThread());
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

    public Observable<BaseResultWrapper<UserBean>> setRegister(String account, String pwd) {
        return mRemoteDataSource.setRegister(account,pwd).compose(this.<BaseResultWrapper<UserBean>>initNetworkThread());
    }

    public Observable<BaseResultWrapper<MemoBean>> setMemoInfo(int userId, String info) {
        return mRemoteDataSource.setMemoInfo(userId,info).compose(this.<BaseResultWrapper<MemoBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<NoteCatalogBean>> setNoteType(int uid, String name,int ispri) {
        return mRemoteDataSource.setNoteType(uid,name,ispri).compose(this.<BaseResultWrapper<NoteCatalogBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<PersionNoteListBean>> getSetListCatalog(int uid, int nid, String title, String info) {
        return mRemoteDataSource.getSetListCatalog(uid,nid,title,info).compose(this.<BaseResultWrapper<PersionNoteListBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<CardBean>> AddCardType(String userId, String name, String imageName, String colorBg) {
        return mRemoteDataSource.AddCardType(userId,name,imageName,colorBg).compose(this.<BaseResultWrapper<CardBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<CardBean>> setReModeCard(String userId, String cid, String name, String imageName, String colorBg) {
        return mRemoteDataSource.setReModeCard(userId,cid,name,imageName,colorBg).compose(this.<BaseResultWrapper<CardBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<VersionBean>> getVersionCode() {
        return mRemoteDataSource.getVersionCode().compose(this.<BaseResultWrapper<VersionBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<StockNoteBean>> getStockMoney(int uid) {
        return mRemoteDataSource.getStockMoney(uid).compose(this.<BaseResultWrapper<StockNoteBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<String>> putStockMoney(int uid, int take_out, int money, int put_in, String remark_money, String stock_code) {
        return mRemoteDataSource.putStockMoney(uid,take_out,money,put_in,remark_money,stock_code).compose(this.<BaseResultWrapper<String>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<String>> putLendRebt(int uid, int money, int mstate, String mremark, String mname, int nowstatu) {
        return  mRemoteDataSource.putLendRebt(uid,money,mstate,mremark,mname,nowstatu).compose(this.<BaseResultWrapper<String>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<LeadDebotBean>> getLendRebt(int uid) {
        return mRemoteDataSource.getLendRebt(uid).compose(this.<BaseResultWrapper<LeadDebotBean>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<String>> getLendRebtStauts(int mid) {
        return mRemoteDataSource.getLendRebtStauts(mid).compose(this.<BaseResultWrapper<String>>initNetworkThread());
    }

    @Override
    public Observable<BaseResultWrapper<ExperienceBean>> getExperienceInfo(@NotNull int uid) {
        return mRemoteDataSource.getExperienceInfo(uid).compose(this.<BaseResultWrapper<ExperienceBean>>initNetworkThread());
    }

    @Override
    public Observable<VideoBean> getRequestHomeData(int num) {
        return mRemoteDataSource.getRequestHomeData(num).compose(this.<VideoBean>initNetworkThread());
    }

    @Override
    public Observable<VideoBean> loadMoreData(@NotNull String date, @NotNull String num) {
        return mRemoteDataSource.loadMoreData(date,num).compose(this.<VideoBean>initNetworkThread());
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
