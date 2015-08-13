package jinesh.urbanhunt_test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jinesh on 21/07/15.
 */
public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    int PAGE_COUNT ;
    private String tabTitles[] = new String[] { "Feed", "Categories", "Stores" };
    private Context context;
    int categoryId;
    Category category;
    ArrayList<String> subCategoriesList;
    ArrayList<String> subCategories;
    String categoryName;
    String collectionName;



    public CategoryPagerAdapter(FragmentManager fm, Context context, String collectionName) {
        super(fm);
        this.context = context;
        this.collectionName = collectionName;
        this.subCategories = getCategories();
        this.PAGE_COUNT = subCategories.size();
    }

    public CategoryPagerAdapter(FragmentManager fm, Context context, int categoryId, String categoryName) {
        super(fm);
        this.context = context;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategories = getSubCategories(categoryId);
        this.PAGE_COUNT = subCategories.size();

    }

    public ArrayList<String> getCategories(){

        subCategoriesList = new ArrayList<>();
        subCategoriesList.add("All");
        subCategoriesList.add("Apparels");
        subCategoriesList.add("Bags");
        subCategoriesList.add("Accesories");
        return subCategoriesList;
    }


    public ArrayList<String> getSubCategories(int categoryId){

//        Array subCategoriesList;
        subCategoriesList = new ArrayList<>();
        switch (categoryId){
            case 1:
                subCategoriesList.add("All");
                subCategoriesList.add("Ethnic Wear");
                subCategoriesList.add("Club Wear");
                Log.d("cat1", ""+subCategoriesList.size());
                return subCategoriesList;
            case 2:
                subCategoriesList.add("All");
                subCategoriesList.add("Tote Bags");
                subCategoriesList.add("Sling Bags");
//                break;
                return subCategoriesList;

        }

        return null;

    }


    public CategoryPagerAdapter(FragmentManager fm, Context context, String brandName, int i) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        ProductListFragment productListFragment = null;
        String subcategory = subCategories.get(position);
//        String categoryName =  category.getCategoryName(categoryId);
//            return productListFragment.newInstance(categoryName,subcategory);
        if(categoryName == null){
            Log.d("categoryName",subcategory);
            return productListFragment.newInstance(collectionName,subcategory,true);
        }else{
            Log.d("subCat",subcategory);
            return productListFragment.newInstance(categoryName,subcategory);}

//            Fragment fragment = null;
//
//            Class fragmentClass;
//            switch (position) {
//                case 0:
//                    fragment = HuntListActivity.newInstance("All");
//                    return fragment;
//                break;
//                case 1:
//                    fragment = SelectCategoryFragment.newInstance();
//                    return fragment;
//                break;
//                case 2:
//                    try {
//                        fragment = FirstFragment.class.newInstance();
//                        return fragment;
//                    } catch (InstantiationException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
////                fragment = BrandFragment.newInstance();
////                return fragment;
//            }
//
//


    }

    @Override
    public int getCount() {
        Log.d("pgsize","" + PAGE_COUNT);
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return subCategories.get(position);
    }
}
