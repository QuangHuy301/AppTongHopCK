package com.example.btgkapptonghop;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ArrayList<Item> arrayItem = new ArrayList<>();
    ItemRecycleAdapter adapter;
    Uri selectedImg=null;
    Button chooseimg;
    // TODO: Rename and change types of parameters

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (arrayItem.size() == 0)
        {
            initDATA();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclesv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        dividerItemDecoration.setDrawable(
                ContextCompat.getDrawable(getContext(), R.drawable.list_divider)
        );
        HomePage homePage = (HomePage) getActivity();
        recyclerView.addItemDecoration(dividerItemDecoration);
//        arrayItem = new ArrayList<>(); // làm cho danh sách tạo lại

        adapter = new ItemRecycleAdapter(arrayItem, getContext(), new ItemRecycleAdapter.IClickItemListener() {
            @Override
            public void onClickItem(Item item) {
                homePage.goToDetailFragment(item);
            }

            @Override
            public void onLongCLickItem(int position) {
                Xoa(position);
            }
        });
        recyclerView.setAdapter(adapter);
        Button additem = view.findViewById(R.id.additem);
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view);
            }
        });
        return view;
    }

    public void showDialog(View view){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_additem);
        EditText username = (EditText) dialog.findViewById(R.id.editTextTextPersonName2);
        EditText description = (EditText) dialog.findViewById(R.id.editTextTextPersonName3);
        Button submit = (Button) dialog.findViewById(R.id.button2);
        chooseimg =(Button) dialog.findViewById(R.id.editTextTextPersonName);
        chooseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                getResult.launch(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImg ==null){
                    arrayItem.add(new Item(R.drawable.quanlyuser,username.getText().toString() ,description.getText().toString()));
                }
                else {
                    arrayItem.add(new Item(selectedImg,username.getText().toString() ,description.getText().toString()));
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private ActivityResultLauncher<Intent> getResult =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    if (result.getResultCode() == Activity.RESULT_OK){
                        selectedImg = data.getData();
                        chooseimg.setText("Đã chọn ảnh");
                    }
                    if (result.getResultCode() == Activity.RESULT_CANCELED){
                        selectedImg = null;
                        chooseimg.setText("Chưa chọn ảnh");
                    }
                }
            }
    );

    private void Xoa(final int  position){
        AlertDialog.Builder alterDialog  = new AlertDialog.Builder(getContext());
        alterDialog.setTitle("Thông báo ");
        alterDialog.setIcon(R.mipmap.ic_launcher);
        alterDialog.setMessage("Bạn có muốn xóa mặt hàng này không ?");
        alterDialog.setPositiveButton("Có", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrayItem.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        alterDialog.setNegativeButton("Không", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alterDialog.show();
    }

    public void initDATA(){
        arrayItem.add(new Item(R.drawable.img_1,"Trà Sữa Dâu Latte","Trái dâu tây được mệnh danh là nữ hoàng của các loại hoa quả dù sử dụng ở bất kỳ hình thức nào từ quả dâu tươi, dâu khô đến mứt dâu… loại trái cây này đều mang đến rất nhiều lợi ích cho sức khoẻ nhờ vào hàm lượng chất dinh dưỡng dồi dào cùng các chất chống oxy hoá. "));
        arrayItem.add(new Item(R.drawable.img_2,"Trà Sữa Xoài Matcha Latte","Sản phẩm là sự kết hợp giữa mứt xoài được nhập khẩu từ Đài Loan. Lớp mứt trong có vị ngọt dịu nhẹ, phần thịt xoài được giữ nguyên từng thớ mang đến cảm giác tự nhiên về nguyên liệu. Phần trà xanh sữa được kết hợp khéo léo cùng topping trân châu giòn dai, mang đến trải nghiệm về dòng thức uống thơm ngon"));
        arrayItem.add(new Item(R.drawable.img_3,"Trà Sữa Okinawa Latte","Thưởng thức trà đen nguyên chất cùng sữa tươi thanh mát kết hợp với đường nâu Okinawa thì còn gì bằng! Một ngụm nhỏ đường nâu ở đáy ly để cảm nhận vị mật đường thanh khiết! Khuấy đều để các nguyên liệu hoà hợp với nhau để cuối tuần tha hồ thưởng thức lại giữ dáng xinh"));
        arrayItem.add(new Item(R.drawable.img_4,"Trà Đen Gongcha","Trà Đen Gong Cha. Sự kết hợp của trà Đen (Hồng Trà) với lớp kem sữa mặn, đem đến một hương vị phong phú và tuyệt vời nhất."));
        arrayItem.add(new Item(R.drawable.img_5,"Trà Sữa Strawberry","Trà sữa Đài Loan luôn nổi tiếng có mùi vị thơm ngon nay còn đóng trong chai xinh xắn đang là trào lưu rất hot hiện nay. Đáp ứng nhu cầu của các bạn trẻ"));
        arrayItem.add(new Item(R.drawable.img_6,"Trà Sữa Okinawa","Thưởng thức trà đen nguyên chất cùng sữa tươi thanh mát kết hợp với đường nâu Okinawa thì còn gì bằng! Một ngụm nhỏ đường nâu ở đáy ly để cảm nhận vị mật đường thanh khiết! Khuấy đều để các nguyên liệu hoà hợp với nhau để cuối tuần tha hồ thưởng thức lại giữ dáng xinh"));
        arrayItem.add(new Item(R.drawable.img_7,"Trà Sữa Matcha Đậu Đỏ","Ly trà sữa matcha mát lạnh, thơm mùi trà xanh cùng vị béo ngậy và ngọt của sữa hòa quyện tạo nên một ly trà sữa ngon tuyệt kích thích mọi giác quan."));
        arrayItem.add(new Item(R.drawable.img_8,"Trà Sữa Khoai Môn","Khoai môn là một loại củ được sử dụng quen thuộc với nhiều dân tộc sống ở khu vực Thái Bình Dương. Trong khoai môn khá giàu chất xơ. Một chén khoai môn luộc cung cấp khoảng 27% chất xơ cho cơ thể hàng ngày."));
    }
}