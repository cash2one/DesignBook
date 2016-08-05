package com.m520it.designbook.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m520it.designbook.R;
import com.m520it.designbook.adapter.GalleryAdapter;
import com.m520it.designbook.bean.GalleryBean;
import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.callback.ResultBeanCallback;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.controller.Controller;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * 梁家明
 * 图库
 * Created by dragon on 2016/7/30.
 */
public class GalleryFragment extends Fragment {

    GridView mGridView;
    @BindView(R.id.btn_first)
    Button mBtnFirst;
    @BindView(R.id.btn_two)
    Button mBtnTwo;
    @BindView(R.id.tv_title_room)
    TextView mTvTitleRoom;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title_style)
    TextView mTvTitleStyle;
    @BindView(R.id.iv_right1)
    ImageView mIvRight1;
    // String[] imagesUrl = new String[]{"http://pic.shejiben.com/i/upload/2016/07/28/20160728133453-8958a958-la.jpg", ""};

    private Myhandler mHandler;
    private GalleryAdapter mAdapter;
  //  private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_gallery, null);
        mGridView = (GridView) view.findViewById(R.id.gallery_listView);


        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHandler = new Myhandler(this);
        initView();
        loadData();
        //TODO

        mBtnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                v1 = LayoutInflater.from(getContext()).inflate(R.layout.galllery_item, null, false);
              // return v1;
            }
        });

        mBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                v2 = LayoutInflater.from(getContext()).inflate(R.layout.activity_gallery_btn, null, false);
            }
        });


        mGridView.setAdapter(mAdapter);

    }

    private void initView() {


        mGridView = (GridView) getActivity().findViewById(R.id.gallery_listView);

        mAdapter = new GalleryAdapter(getContext());
    }

    private void loadData() {
        ResultBeanCallback<List<GalleryBean.DataEntity>> callback = new ResultBeanCallback<List<GalleryBean.DataEntity>>() {
            @Override
            public ResultBean<List<GalleryBean.DataEntity>> parseNetworkResponse(Response response, int id) throws Exception {
//                new JsonObject()
                ResultBean<List<GalleryBean.DataEntity>> resultBean = new Gson().fromJson(response.body().string(),
                        new TypeToken<ResultBean<List<GalleryBean.DataEntity>>>() {
                        }.getType());
                return resultBean;
            }
        };
        callback.setResultListener(new IResultBeanListener<List<GalleryBean.DataEntity>>() {
            @Override
            public void onError(String errorMsg, int id) {
                if ("".equals(errorMsg)) {
                    errorMsg = "请求错误";
                }
                ToastUtil.showToast(getActivity(), errorMsg);
            }

            @Override
            public void onSucceed(ResultBean<List<GalleryBean.DataEntity>> response, int id) {
                Message.obtain(mHandler, id, response.getData()).sendToTarget();


            }
        });


        Controller.doGetAsync(NetWorkCons.GALLERY_URL, null, IDMessage.GALLERY_LOAD_CASE, callback);


    }

    private void setGalleryList(List<GalleryBean.DataEntity> gallery) {
        mAdapter.setList(gallery);
        mAdapter.notifyDataSetChanged();
    }


    private static class Myhandler extends Handler {

        WeakReference<GalleryFragment> mFrag;

        Myhandler(GalleryFragment frag) {
            this.mFrag = new WeakReference<GalleryFragment>(frag);
        }

        public void handleMessage(Message msg) {
            if (mFrag.get() == null) {
                return;
            }
            switch (msg.what) {
                case IDMessage.GALLERY_LOAD_CASE:
                    mFrag.get().setGalleryList((List<GalleryBean.DataEntity>) msg.obj);
                    break;
                //-------------
            }
        }
    }
}