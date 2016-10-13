package sportslottery.gooolal.com.sportslotteryforshanghai.aplication;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
/**
 * ================================================
 * 作    者：booob
 * 版    本：1.0
 * 创建日期：2016-10-12-0012 上午 11:54
 * 描    述：
 * 修订历史：
 * ================================================
 */

public class BaseApplication extends Application {

	// 获取到主线程的上下文
	private static BaseApplication mContext = null;
	// 获取到主线程的handler
	private static Handler mMainThreadHandler = null;

	// 获取到主线程
	private static Thread mMainThread = null;
	// 获取到主线程的id
	private static int mMainThreadId;
	// 获取到主线程的looper
	private static Looper mMainThreadLooper = null;

	@Override
	public void onCreate() {
		super.onCreate();
		this.mContext = this;
		this.mMainThreadHandler = new Handler();
		this.mMainThread = Thread.currentThread();
		this.mMainThreadId = android.os.Process.myTid();
		this.mMainThreadLooper = getMainLooper();

		//必须调用初始化
		OkGo.init(this);

		//以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
		//好处是全局参数统一,特定请求可以特别定制参数
		try {
			//以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
			OkGo.getInstance()

					//打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
					.debug("OkGo")

					//如果使用默认的 60秒,以下三行也不需要传
					.setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
					.setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
					.setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

					//可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
					.setCacheMode(CacheMode.NO_CACHE)

					//可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
					.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

					//如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
					.setCookieStore(new PersistentCookieStore());          //cookie持久化存储，如果cookie不过期，则一直有效

					//可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
//                    .setCertificates()                                  //方法一：信任所有证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

					//可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

					/*//这两行同上,不需要就不要传
					.addCommonHeaders(headers)                                         //设置全局公共头
					.addCommonParams(params);                                          //设置全局公共参数*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 对外暴露上下文
	public static BaseApplication getApplication() {
		return mContext;
	}

	// 对外暴露主线程的handler
	public static Handler getMainThreadHandler() {
		return mMainThreadHandler;
	}

	// 对外暴露主线程
	public static Thread getMainThread() {
		return mMainThread;
	}

	// 对外暴露主线程id
	public static int getMainThreadId() {
		return mMainThreadId;
	}

	// 对外暴露主线程的looper
	public static Looper getMainThreadLooper() {
		return mMainThreadLooper;
	}
}

