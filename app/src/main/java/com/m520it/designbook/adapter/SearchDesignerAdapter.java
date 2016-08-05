package com.m520it.designbook.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.R;
import com.m520it.designbook.bean.DesignerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/30 21:02
 * @desc 设计师搜索页面的分配器
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */
public class SearchDesignerAdapter extends BaseAdapter {
    private Context mContext;
    private List<DesignerBean> mDatas=new ArrayList<>();//收索设计师的信息

    public SearchDesignerAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(List<DesignerBean> dataas){
        mDatas=dataas;
    }

    public void addData(List<DesignerBean> data){
        mDatas.addAll(data);
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mDatas != null ? mDatas.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return mDatas != null ? mDatas.get(i).getUid() : null;
    }

    /**
     * 获取数据的操作
     * @param position
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.screening_designer, null);
            holder = new ViewHolder();
            /**-------城市-----**/
            holder.designer_city = (TextView) view.findViewById(R.id.designer_city);
            /**-------知名-----**/
            holder.designer_famous = (ImageView) view.findViewById(R.id.designer_famous);

            /**-------头像-----**/
            holder.designer_head = (SimpleDraweeView) view.findViewById(R.id.designer_head);
            /**--------名字----**/
            holder.designer_name = (TextView) view.findViewById(R.id.designer_name);
            /**--------预约人数----**/
            holder.designer_number = (TextView) view.findViewById(R.id.designer_number);
            /**--------认证----**/
            holder.designer_person = (ImageView) view.findViewById(R.id.designer_person);
            /**--------价格区间----**/
            holder.designer_price = (TextView) view.findViewById(R.id.designer_price);
            /**---------省---**/
            holder.designer_province = (TextView) view.findViewById(R.id.designer_province);
            /**---------单位---**/
            holder.designer_unit = (TextView) view.findViewById(R.id.designer_unit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        /**------获取position位置的数据*/
        DesignerBean datas = mDatas.get(position);

        /**-------添加数据-----**/
        holder.designer_city.setText(datas.getCity());

        /**加载圆形图片----->圆形图片**/
        Uri parse = Uri.parse(datas.getFacePic());
        holder.designer_head.setImageURI(parse);

        //设计图标  认证和知名
        String goodlevelCN = datas.getGoodlevelCN();
        if(goodlevelCN.contains("F")){
            holder.designer_famous.setImageResource(R.mipmap.ico_f);
        }else if(goodlevelCN.contains("E")){
            holder.designer_famous.setImageResource(R.mipmap.ico_e);
        }else if(goodlevelCN.contains("N")){
            holder.designer_famous.setImageResource(R.mipmap.ico_n);
        }else {
            holder.designer_famous.setVisibility(View.GONE);
        }

        String rzCN = datas.getRzCN();
        if(rzCN.contains("个人认证")){
            holder.designer_person.setImageResource(R.mipmap.ico_v_designer);
        }else if(rzCN.equals("企业认证")){
            holder.designer_person.setImageResource(R.mipmap.ico_v_proprietor);
        }else {
            holder.designer_person.setVisibility(View.GONE);
        }

        holder.designer_name.setText(datas.getNick());
        holder.designer_number.setText(datas.getYuyueNum() + "预约");
        holder.designer_price.setText(datas.getPriceRange());
        holder.designer_province.setText(datas.getShen());
        holder.designer_unit.setText(datas.getPriceUnit());
        return view;
    }

    class ViewHolder {
        SimpleDraweeView designer_head;//头像
        TextView designer_name;//设计师名
        ImageView designer_famous;//知名
        ImageView designer_person;//认证
        TextView designer_province;//省
        TextView designer_city;//市
        TextView designer_number;//预约数
        TextView designer_price;//价格区间
        TextView designer_unit;//单位
    }
}
