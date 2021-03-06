package kohn.votesmart;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTest {
	public static void main(String [] args)throws IOException{
		
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://api.votesmart.org")	
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		VoteSmartService service = retrofit.create(VoteSmartService.class);
		//getStateIDs(retrofit, service);
		getCandidatebyZipCode("11230","3341", retrofit, service);
		//getBillsByState(retrofit, service, "2017","NY");
	}
	public static void getCandidatebyZipCode(String zip5, String zip4, Retrofit retrofit, VoteSmartService service ) {
		
		
		Call<VoteSmartModel> call = service.getCandidatesbyZipCode(zip5, zip4);
		
		call.enqueue(new Callback<VoteSmartModel>() {

			@Override
			public void onResponse(Call<VoteSmartModel> call, Response<VoteSmartModel> response) {
				
				VoteSmartModel feed = response.body();
				
				System.out.println(feed.getCandidateList().getCandidate());
				
			}

			@Override
			public void onFailure(Call<VoteSmartModel> call, Throwable t) {		
				t.printStackTrace();
			}			
		});
	}
	
	public static void  getStateIDs(Retrofit retrofit, VoteSmartService service ) {
		Call<VoteSmartModel> call = service.getStateIDs();
		call.enqueue(new Callback<VoteSmartModel>(){

			@Override
			public void onResponse(Call<VoteSmartModel> call, Response<VoteSmartModel> response) {
				VoteSmartModel feed = response.body();
				System.out.println(feed.getStateList().getList());
			}

			@Override
			public void onFailure(Call<VoteSmartModel> call, Throwable t) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public static void  getBillsByState(Retrofit retrofit, VoteSmartService service, String year, String stateId) {
		//Call<VoteSmartModel> call = service.getBillsByState("4621bd0bc679f84d6eee42c99c643e57", year, stateId);
		Call<VoteSmartModel> call = service.getRecentBills("40");

		call.enqueue(new Callback<VoteSmartModel>(){

			@Override
			public void onResponse(Call<VoteSmartModel> call, Response<VoteSmartModel> response) {
				VoteSmartModel feed = response.body();
				//System.out.println(feed.getBills());

			}

			@Override
			public void onFailure(Call<VoteSmartModel> call, Throwable t) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
