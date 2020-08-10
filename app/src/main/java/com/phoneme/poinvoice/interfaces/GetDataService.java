package com.phoneme.poinvoice.interfaces;

//import com.phoneme.ticketing.BuildConfig;
//import com.phoneme.ticketing.ui.company.model.CompanyModel;
//import com.phoneme.ticketing.ui.company.network.CompanyCreatePostResponse;
//import com.phoneme.ticketing.ui.company.network.CompanyEditGetResponse;
//import com.phoneme.ticketing.ui.company.network.CompanyEditPostResponse;
//import com.phoneme.ticketing.ui.dashboard.DashboardApi;
//import com.phoneme.ticketing.ui.dashboard.network.DashboardMainResponse;
//import com.phoneme.ticketing.ui.inframonitoring.InfraMonitoringResponse;
//import com.phoneme.ticketing.ui.productivity.network.TechsupportDashboardResponse;
//import com.phoneme.ticketing.ui.productivity.network.TechsupportDashboardResponseCheck;
//import com.phoneme.ticketing.ui.productivity.network.TechsupportDashboardResponseList;
//import com.phoneme.ticketing.ui.project.network.ProjectAddGetResponse;
//import com.phoneme.ticketing.ui.project.network.ProjectAddPostResponse;
//import com.phoneme.ticketing.ui.project.network.ProjectEditGetResponse;
//import com.phoneme.ticketing.ui.project.network.ProjectEditPostResponse;
//import com.phoneme.ticketing.ui.project.network.ProjectListResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketCreatGetResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketCreatePostResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketEditPostResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketEditResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketGetViewResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketPostViewResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketResponse;
//import com.phoneme.ticketing.ui.ticketing.network.TicketSingleResponse;
//import com.phoneme.ticketing.ui.user.network.UserAddPostResponse;
//import com.phoneme.ticketing.ui.user.network.UserEditGetResponse;
//import com.phoneme.ticketing.ui.user.network.UserEditResponse;
//import com.phoneme.ticketing.ui.user.network.UserListResponse;
//import com.phoneme.ticketing.ui.user.network.UserPasswordChangeResponse;
//import com.phoneme.ticketing.user.network.FCMTokenUploadResponse;
//import com.phoneme.ticketing.user.network.OTPVerifactionResponse;
//import com.phoneme.ticketing.user.network.PhonePostResponse;

import com.phoneme.poinvoice.ui.invoice.network.CheckInvoiceListResponse1;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceListResponse;
import com.phoneme.poinvoice.ui.invoice.network.InvoiceResponse;
import com.phoneme.poinvoice.ui.invoice.network.PoPaymentPOSTResponse;
import com.phoneme.poinvoice.ui.invoice.network.PoUploadPOSTResponse;
import com.phoneme.poinvoice.ui.invoice.network.UPloadPOPaymentGetResponse;
import com.phoneme.poinvoice.ui.invoice.network.VendorAddPostResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListPOPaymentGetResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListPOPaymentPostResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListResponse;
import com.phoneme.poinvoice.ui.po.network.GeneratedListCompleteResponse;
import com.phoneme.poinvoice.ui.po.network.InvoiceAddPostResponse;
import com.phoneme.poinvoice.ui.po.network.PoTemplateEditGETResponse;
import com.phoneme.poinvoice.ui.po.network.PoTemplateEditPOSTResponse;
import com.phoneme.poinvoice.ui.po.network.PoTemplateListResponse;
import com.phoneme.poinvoice.ui.po.network.VendorAddGetResponse;
import com.phoneme.poinvoice.ui.po.network.VendorEditGetResponse;
import com.phoneme.poinvoice.ui.po.network.VendorEditPostResponse;
import com.phoneme.poinvoice.ui.po.network.VendorListResponse;
import com.phoneme.poinvoice.ui.invoice.network.UploadPOGetResponse;
import com.phoneme.poinvoice.user.network.OTPVerifactionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Path;

public interface GetDataService {
//    // https://phoneme.in/android/napolean
    //UploadPOGetResponse
    @GET("/invoiceapis/invoice/uploadpo")
    Call<UploadPOGetResponse> getPOUploadData(@Query("id") String id);

    @GET("/invoiceapis/invoice/invoicelist/")
    Call<InvoiceListResponse> getInvoiceList();

    @GET("/invoiceapis/Invoice/uploadinvoicepayment")
    Call<UPloadPOPaymentGetResponse> getPOPaymentUploadData(@Query("id") String id);

    @GET("/invoiceapis/vendor/create")
    Call<VendorAddGetResponse> getVendorAddData();

    @GET("/invoiceapis/vendor/vendorlist")
    Call<VendorListResponse> getVendorList();

    @GET("/invoiceapis/vendor/vendorlist")
    Call<VendorListResponse> getVendorList_Page(@Query("page") int page);

    @GET("/invoiceapis/vendor/edit")
    Call<VendorEditGetResponse> getVendorEditData(@Query("id") String id);

    @GET("/invoiceapis/po/")
    Call<GeneratedListResponse> getGeneratedList();

    @GET("/invoiceapis/Po/polist/{yr}")
    Call<GeneratedListCompleteResponse> getGeneratedListComplete(@Path("yr") String year);

    @GET("/invoiceapis/Po/uploadpayment")
    Call<GeneratedListPOPaymentGetResponse> getGeneratedListPOPaymentData(@Query("id") String id);


    @GET("/invoiceapis/po/potemplate2")
    Call<PoTemplateListResponse> getPoTemplateList();

    @GET("/invoiceapis/PoTemplate/edit")
    Call<PoTemplateEditGETResponse> getPoTemplateEditData(@Query("id") String id);





    @FormUrlEncoded
    @POST("/invoiceapis/po/uploadinvoice")
    Call<InvoiceAddPostResponse> postInvoiceAdd(@FieldMap Map<String, String> invoiceData);

    @FormUrlEncoded
    @POST("/invoiceapis/vendor/create")
    Call<VendorEditPostResponse> postVendorEdit(@FieldMap Map<String, String> vendorData);


   @FormUrlEncoded
    @POST("/invoiceapis/vendor/create")
    Call<VendorAddPostResponse> postVendorAdd(@FieldMap Map<String, String> vendorData);


    @Multipart
    @POST("/invoiceapis/PoTemplate/edit")
    Call<PoTemplateEditPOSTResponse> postPOTemplateWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);

    @Multipart
    @POST("/invoiceapis/PoTemplate/edit")
    Call<PoTemplateEditPOSTResponse> postPOTemplateWithoutImage(@PartMap() Map<String, RequestBody> partMap);


    @Multipart
    @POST("/invoiceapis/invoice/uploadpo")
    Call<PoUploadPOSTResponse> postPOUploadWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);

    @Multipart
    @POST("/invoiceapis/invoice/uploadpo")
    Call<PoUploadPOSTResponse> postPOUploadWithoutImage(@PartMap() Map<String, RequestBody> partMap);

    @Multipart
    @POST("/invoiceapis/Invoice/uploadinvoicepayment")
    Call<PoPaymentPOSTResponse> postPOPaymentWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);

    @Multipart
    @POST("/invoiceapis/Invoice/uploadinvoicepayment")
    Call<PoPaymentPOSTResponse> postPOPaymentWithoutImage(@PartMap() Map<String, RequestBody> partMap);


    @Multipart
    @POST("/invoiceapis/po/uploadpayment")
    Call<GeneratedListPOPaymentPostResponse> postGeneratedListPOPaymentWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);

    @Multipart
    @POST("/invoiceapis/po/uploadpayment")
    Call<GeneratedListPOPaymentPostResponse> postGeneratedListPOPaymentWithoutImage(@PartMap() Map<String, RequestBody> partMap);





//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/dashboard/index1")
//    Call<DashboardApi> getDashboardData();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/dashboard/index")
//    Call<DashboardMainResponse> getDashboardMainData();
//
////    @GET("/android/napolean/ticketingapici/index.php/ticket/ticket") //without authorization. It is working
////    Call<TicketResponse> getTickets();
//
////    @GET("/android/napolean/ticketingapici/index.php/ticket/index")
////    Call<TicketResponse> getTickets();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/index")
//    Call<TicketResponse> getTickets();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/index1")
//    Call<TicketResponse> getMyTickets();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/company/")
//    Call<List<CompanyModel>> getCompanies();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/company/edit")
//    Call<CompanyEditGetResponse> getCompanyData(@Query("id") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php//ticket/view1")
//    Call<TicketSingleResponse> getTicketView1(@Query("id") String id);
//
////    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/productivity/")//This one original. Nnot to be used for a while
////    Call<TechsupportDashboardResponse> getProductivity();//because of biru changes
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/productivity/index2")//This one original not to be used for a while
//    Call<TechsupportDashboardResponse> getProductivity();//because of biru changes
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/productivity/index4")//This one original not to be used for a while
//    Call<TechsupportDashboardResponseCheck> getProductivityCheck();//because of biru changes
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/productivity/")
//    Call<TechsupportDashboardResponse> getUserProductivity(@Query("uid") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/productivity/index2")//Befor biru changes it was /index or just /
//    Call<TechsupportDashboardResponseList> getUserProductivityList(@Query("uid") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/productivity/index3")//Befor biru changes it was /index or just /
//    Call<TechsupportDashboardResponseList> getUserProductivityListWithStatus(@Query("uid") String id, @Query("solved") String status);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/")
//    Call<UserListResponse> getUserList();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/getGCMAppString")
//    Call<String> getGCMMasterDataForaUser(@Query("gcm_master_id") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/edit")
//    Call<UserEditGetResponse> getUserData(@Query("id") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/edit")
//    Call<TicketEditResponse> getTicketEdit(@Query("id") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/view")
//    Call<TicketGetViewResponse> getTicketView(@Query("id") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/create")
//    Call<TicketCreatGetResponse> getTicketCreate();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/")
//    Call<ProjectListResponse> getProjectList();
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/edit")
//    Call<ProjectEditGetResponse> getProjectEdit(@Query("id") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/listoftickets")
//    Call<TicketResponse> getTicketsForaGivenProject(@Query("id") String id);
//
//    @GET("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/add")
//    Call<ProjectAddGetResponse> getDataForProjectAdd();
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/view")
//    Call<TicketPostViewResponse> postTicketView(@FieldMap Map<String, String> postTicketData);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/add")
//    Call<ProjectAddPostResponse> postProjectAdd(@FieldMap Map<String, String> projectData, @Field("allocated_users[]") List<String> items);
//
//    //Added feb 20 2020 post biru changes
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/add")
//    Call<ProjectAddPostResponse> postProjectAddWithOutImage(@PartMap() Map<String, RequestBody> partMap, @Query("allocated_users[]") List<String> user_id);
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/add")
//    Call<ProjectAddPostResponse> postProjectAddWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap, @Query("allocated_users[]") List<String> user_id);
//
////    @FormUrlEncoded
////    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/editpost")
////    Call<ProjectEditPostResponse> postCompanyEdit(@FieldMap Map<String, String> proejectData);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/editpost")
//    Call<ProjectEditPostResponse> postCompanyEdit2(@FieldMap Map<String, String> projectData, @FieldMap Map<String, List<String>> map2);
//
////    @FormUrlEncoded //commented on dec26 2019
////    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/editpost")
////    Call<ProjectEditPostResponse> postCompanyEdit(@FieldMap Map<String,String> projectData,@Field("allocated_users[]") List<String> items);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/project/edit")
//    Call<ProjectEditPostResponse> postCompanyEdit(@FieldMap Map<String, String> projectData, @Field("allocated_users[]") List<String> items);
//
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/company/create")
//    Call<CompanyCreatePostResponse> postCompanyCreate(@Field("Company_name") String name, @Field("status") int status);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/company/edit")
//    Call<CompanyEditPostResponse> postCompanyEditFinal(@Field("id") String id, @Field("Company_name") String name, @Field("status") int status);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/create")
//    Call<TicketCreatePostResponse> postTicketAdd(@FieldMap Map<String, String> ticketData);
//
//    //Biru user allocated
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/create")
//    Call<TicketCreatePostResponse> postTicketAddWithUser(@FieldMap Map<String, String> ticketData, @Field("check_user[]") List<String> chkusrs);
//
////    @FormUrlEncoded
////    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/create")
////    Call<TicketCreatePostResponse> postTicketAddWithImage(@FieldMap Map<String, String> ticketData);
//
////    @Multipart
////    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/create")
////    Call<TicketCreatePostResponse> postTicketAddWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap,@Query("check_user[]") ArrayList<String> user_id);
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/create")
//    Call<TicketCreatePostResponse> postTicketAddWithOutImage(@PartMap() Map<String, RequestBody> partMap, @Part("check_user[]") ArrayList<Integer> user_id);
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/create")
//    Call<TicketCreatePostResponse> postTicketAddWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap, @Part("check_user[]") ArrayList<Integer> user_id);
//
//
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/otpverification")
//    Call<OTPVerifactionResponse> postOTP(@FieldMap Map<String, String> otpData);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/passwordchange")
//    Call<UserPasswordChangeResponse> postPasswordChange(@FieldMap Map<String, String> pwdData);

    //This one will be used
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/login/index")
//    Call<OTPVerifactionResponse> postLoginPassword(@FieldMap Map<String, String> otpData);

    @FormUrlEncoded
    @POST("/invoiceapis/login/login")
    Call<OTPVerifactionResponse> postLoginPassword(@FieldMap Map<String, String> otpData);

//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/postphoneforlogin")
//    Call<PhonePostResponse> postPhone(@FieldMap Map<String, String> phone);
//
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/financeGCM")
//    Call<FCMTokenUploadResponse> postFCMToken(@FieldMap Map<String, String> userData);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/financeGCM")
//    Call<String> postCompletUserData(@FieldMap Map<String, String> userData);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/postUpdateUserDataTable")
//    Call<String> postUpdateUserDataTable(@FieldMap Map<String, String> userData);
//
//    @FormUrlEncoded
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/updateGCMMasterTable")
//    Call<String> postUpdateGCMTable(@FieldMap Map<String, String> userData);
//
//    //commented on dec 26 2019
////    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/editpost")
////    @FormUrlEncoded
////    Call<TicketEditPostResponse> postTicketEdit(@Field("ticket_title") String name,
////                                                @Field("ticket_desc") String description,
////                                                @Field("project_id") String projectid,
////                                                @Field("id") String id,
////                                                @Field("priority") String priority,
////                                                @Field("last_updated_by") int lastupdatedby);
//
//    //Added on dec26 2019
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/edit")
//    @FormUrlEncoded
//    Call<TicketEditPostResponse> postTicketEdit(@Field("ticket_title") String name,
//                                                @Field("ticket_desc") String description,
//                                                @Field("project_id") String projectid,
//                                                @Field("id") String id,
//                                                @Field("priority") String priority,
//                                                @Field("last_updated_by") int lastupdatedby);
//
//    //Added on dec26 2019
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/edit")
//    @FormUrlEncoded
//    Call<TicketEditPostResponse> postTicketEditMap(@FieldMap Map<String, String> ticketEditData);
//
//    //Added on feb 14 2020
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/ticket/edit")
//    @FormUrlEncoded
//    Call<TicketEditPostResponse> postTicketEditMapUser(@FieldMap Map<String, String> ticketEditData, @Field("check_user[]") List<String> chkusrs);
//
//
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/posteditwithoutimage")
//    @FormUrlEncoded
//    Call<UserEditResponse> postUserdata2(@Field("name") String name,
//                                         @Field("mobilenum") String mobilenum,
//                                         @Field("status") String status_new,
//                                         @Field("id") String id,
//                                         @Field("email") String email);
//
//
//    //This '/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/profile' to be used instead of     @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/posteditwithoutimage")
////    @Multipart
////    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/profile")
////    @FormUrlEncoded
////    Call<UserEditResponse> postUserdataWithImage(@Part MultipartBody.Part file,@Part Map<String, RequestBody> userData);
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/profile")
//    Call<UserEditResponse> postUserdataWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);
//
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/profile")
//    Call<UserEditResponse> postUserdataWithoutImage(@PartMap() Map<String, RequestBody> userData);
//
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/add")
//    Call<UserAddPostResponse> postUserAddWithImage(@Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);
//
////    @Multipart
////    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/profile")
////    Call< UserAddPostResponse> postUserAddWithoutImage(@PartMap() Map<String,RequestBody> userData);
//
//    @Multipart
//    @POST("/android/napolean"+ BuildConfig.MIDDLE_URL +"index.php/user/add")
//    Call<UserAddPostResponse> postUserAddWithoutImage(@PartMap() Map<String, RequestBody> userData);
//
//
//
//
//    @GET("/anujitbhu/ticketing/API/serverlogsmobile")//Need to change url  //url changed
//    Call<InfraMonitoringResponse> getInframonitoringData();
//
//    @POST("/anujitbhu/ticketing/API/serverlogsmobile") //Need to change url //url changed
//    @FormUrlEncoded
//    Call<InfraMonitoringResponse> postInframonitoringData(@FieldMap Map<String, String> ticketEditData);
//
//    @POST("/logs/executemobile.php") //Need to change url //url changed
//    @FormUrlEncoded
//    Call<String> postInframonitoringDataCommand(@FieldMap Map<String, String> ticketEditData);

}
