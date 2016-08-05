package com.m520it.designbook.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.R;
import com.m520it.designbook.bean.CasesBean;

import java.util.List;

/**
 * @author JJ
 * @time 2016/7/30 0030  14:41
 * @desc ${TODD}
 */
public abstract class CaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<CasesBean> mCaseList;//案例数据列表

    public CaseAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<CasesBean> caseList) {
        mCaseList = caseList;
    }

    public void addData(List<CasesBean> caseList) {
        mCaseList.addAll(caseList);
    }

    /**
     * 打开设计师主页
     *
     * @param cases
     */
    protected abstract void openDesignerIndex(CasesBean cases);

    /**
     * 打开全部案例页面
     */
    public abstract void openAllCaseActivity();


    /**
     * 有个热门推荐的view
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {//热点推荐
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getCount() {
        return mCaseList != null ? mCaseList.size() + 1 : 0;
    }

    @Override
    public Object getItem(int position) {
        return mCaseList.get(position - 1);
    }

    @Override
    public long getItemId(int position) {
        return position - 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        switch (type) {
            case 0://热点推荐
                HotViewHolder hotHoler;
                if (convertView == null) {
                    hotHoler = new HotViewHolder(View.inflate(mContext, R.layout.item_hot, null));
                    convertView = hotHoler.rootView;
                } else {
                    hotHoler = (HotViewHolder) convertView.getTag();
                }
                hotHoler.rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击打开全部案例页面
                        openAllCaseActivity();
                    }
                });
                break;


            case 1://案例
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder(View.inflate(mContext, R.layout.item_case, null));
                    convertView = holder.rootView;
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                position -= 1;
                final CasesBean cases = mCaseList.get(position);
                //加载案例图片
                holder.iv_photo.setImageURI(Uri.parse(cases.getImgUrl()));
                //加载头像
                holder.iv_designerIcon.setImageURI(Uri.parse(cases.getFacePic()));

                holder.tv_name.setText(cases.getName());
                holder.tv_detail.setText(new StringBuffer().append(cases.getAreaName())
                        .append("/")
                        .append(cases.getStyleName())
                        .append("/")
                        .append(cases.getSpaceName())
                        .append("/")
                        .append(cases.getCounts())
                        .append("图").toString());

                holder.iv_designerIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDesignerIndex(cases);
                    }
                });
                break;
        }
        return convertView;
    }


    /**
     * 热门推荐
     */
    private static class HotViewHolder {
        public View rootView;

        public HotViewHolder(View rootView) {
            this.rootView = rootView;
            this.rootView.setTag(this);
        }
    }

    /**
     * 案例holder
     */
    private static class ViewHolder {
        public View rootView;
        public SimpleDraweeView iv_photo;//图片
        public SimpleDraweeView iv_designerIcon;//设计师头像
        public TextView tv_name;//项目名
        public TextView tv_detail;//详细信息

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_photo = (SimpleDraweeView) rootView.findViewById(R.id.iv_photo);
            this.iv_designerIcon = (SimpleDraweeView) rootView.findViewById(R.id.iv_designerIcon);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_detail = (TextView) rootView.findViewById(R.id.tv_detail);
            this.rootView.setTag(this);
        }
    }
}
