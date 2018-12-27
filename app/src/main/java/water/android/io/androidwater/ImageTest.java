package water.android.io.androidwater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

public class ImageTest {

    public static int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        // 此时返回的bitmap为null
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        /**
         *options.outHeight为原始图片的高
         */
        int[] size = new int[]{options.outWidth, options.outHeight};

        return size;
    }

    /**
     * 测试获取网络图片宽高:
     * 结论：无法获取图片宽高
     */
    public static void test1() {
        String url = "/storage/emulated/0/$MuMu共享文件夹/2018102550725507.jpg";

        File file = new File(url);
        System.out.println("123 URI:"+file.toURI());

        int[] size = getImageWidthHeight(file.toURI().toString());
        System.out.println("123 size.w:" + size[0] + ",size.h:" + size[1]);
    }

    /**
     * 通过Glide
     */
    public static void test2(Context context, ImageView imageView) {
        String url = "http://img02.tooopen.com/images/20160413/tooopen_sy_159215748672.jpg";
    }
}
