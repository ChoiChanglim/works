package my.random.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import my.random.api.constant.LottoReader;
import my.random.api.constant.YourLucky;
import my.random.api.constant.YourLucky.BasicSetting;
import my.random.api.exception.CustomException;
import my.random.bean.CustomSetting;
import my.random.bean.DestinyInfo;
import my.random.bean.Luckylog;
import my.random.bean.LuckylogExample;
import my.random.bean.WinNumber;
import my.random.mapper.DestinyInfoMapper;
import my.random.mapper.LuckylogMapper;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value="transactionManager", readOnly=false)
public class DestinyServiceImpl implements DestinyService{
    @Autowired
    DestinyInfoMapper destinyInfoMapper;

    @Autowired
    LuckylogMapper luckylogMapper;

    @Override
    public DestinyInfo getLastDestinyInfo(){
        DestinyInfo destinyInfo = destinyInfoMapper.getLastDestinyInfo();
        return destinyInfo;
    }

    @Override
    public DestinyInfo getDestinyInfoByGno(int gno) {
        DestinyInfo destinyInfo = destinyInfoMapper.selectByPrimaryKey(gno);
        return destinyInfo;
    }

    @Override
    public void insert(DestinyInfo destinyInfo) {
        destinyInfoMapper.insert(destinyInfo);
    }

    @Override
    public void insertRange(int startGno, int endGno) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("method", "allWinExel");
        param.put("drwNoStart", String.valueOf(startGno));
        param.put("drwNoEnd", String.valueOf(endGno));


        List<WinNumber> list = LottoReader.ReadWinnumberResult(param);
        Iterator<WinNumber> iter = list.iterator();
        while(iter.hasNext()){
            WinNumber winNumber = iter.next();

            DestinyInfo destinyInfo = new DestinyInfo();
            destinyInfo.setGno(winNumber.getGno());
            destinyInfo.setNo1(winNumber.getNums().get(0));
            destinyInfo.setNo2(winNumber.getNums().get(1));
            destinyInfo.setNo3(winNumber.getNums().get(2));
            destinyInfo.setNo4(winNumber.getNums().get(3));
            destinyInfo.setNo5(winNumber.getNums().get(4));
            destinyInfo.setNo6(winNumber.getNums().get(5));
            destinyInfo.setBonus(winNumber.getBonus());
            destinyInfo.setFirstCount(winNumber.getFirstCount());
            destinyInfo.setFirstAmount(winNumber.getFirstAmount());
            destinyInfo.setSecondCoount(winNumber.getSecondCount());
            destinyInfo.setSecondAmount(winNumber.getSecondAmount());
            destinyInfo.setThirdCount(winNumber.getThirdCount());
            destinyInfo.setThirdAmount(winNumber.getThirdAmount());
            destinyInfo.setGdate(new Date());

            insert(destinyInfo);
        }

    }

    /**
     * 생성
     */
    @Override
    public List<Integer[]> create(String ukey, int count) {
        DestinyInfo destinyInfo = getLastDestinyInfo();
        List<Integer> lastnum = new ArrayList<Integer>();
        lastnum.add(destinyInfo.getNo1());
        lastnum.add(destinyInfo.getNo2());
        lastnum.add(destinyInfo.getNo3());
        lastnum.add(destinyInfo.getNo4());
        lastnum.add(destinyInfo.getNo5());
        lastnum.add(destinyInfo.getNo6());


        List<Integer[]> result = new ArrayList<Integer[]>();
        for(int i=0;i<count;i++){
        	
            Integer[] nums = YourLucky.getNums(BasicSetting.Odd_per.getPercent(), BasicSetting.Even_per.getPercent(), BasicSetting.Continuity_per.getPercent(), BasicSetting.Lastnum_per.getPercent(), lastnum, BasicSetting.ExceptLocation.getPercent());
            result.add(nums);
            Luckylog log = new Luckylog();
            log.setGno(LottoReader.getGnum(new Date()));
            log.setNick(ukey);
            log.setNo1(nums[0]);
            log.setNo2(nums[1]);
            log.setNo3(nums[2]);
            log.setNo4(nums[3]);
            log.setNo5(nums[4]);
            log.setNo6(nums[5]);
            log.setRegdate(new Date());
            log.setGrade(0);
            luckylogMapper.insert(log);
            
        }
        return result;
    }

    @Override
    public DestinyInfo getLastinfo() {
        int gnum = LottoReader.getGnum(new Date());
        DestinyInfo destinyInfo = destinyInfoMapper.selectByPrimaryKey(gnum);
        if(null == destinyInfo){
            throw CustomException.EMPTY_DESTINYINFO;
        }
        return  destinyInfo;
    }

    @Override
    public List<Integer[]> create(String ukey, int count, CustomSetting setting) {
        DestinyInfo destinyInfo = getLastDestinyInfo();
        List<Integer> lastnum = new ArrayList<Integer>();
        lastnum.add(destinyInfo.getNo1());
        lastnum.add(destinyInfo.getNo2());
        lastnum.add(destinyInfo.getNo3());
        lastnum.add(destinyInfo.getNo4());
        lastnum.add(destinyInfo.getNo5());
        lastnum.add(destinyInfo.getNo6());
        List<Integer[]> result = new ArrayList<Integer[]>();
        for(int i=0;i<count;i++){
            Integer[] nums = YourLucky.getNums(setting.getOdd_per(), setting.getEven_per(), setting.getContinuity_per(), setting.getLastnum_per(), lastnum, setting.getExceptLocation());
            result.add(nums);
            Luckylog log = new Luckylog();
            log.setGno(LottoReader.getGnum(new Date()));
            log.setNick(ukey);
            log.setNo1(nums[0]);
            log.setNo2(nums[1]);
            log.setNo3(nums[2]);
            log.setNo4(nums[3]);
            log.setNo5(nums[4]);
            log.setNo6(nums[5]);
            log.setRegdate(new Date());
            log.setGrade(0);
            luckylogMapper.insert(log);
        }
        return result;
    }

	@Override
	public List<Luckylog> getMyList(String ukey, int gno) {
		LuckylogExample example = new LuckylogExample();
		example.createCriteria().andGnoEqualTo(gno).andNickEqualTo(ukey);
		example.setOrderByClause("seq desc");
		List<Luckylog> list = luckylogMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public synchronized List<List<Integer>> getMyLuckyList(String ukey) {
		LuckylogExample example = new LuckylogExample();
		DestinyInfo lastInfo = getLastDestinyInfo();
		int lastGno = lastInfo.getGno();
		
		example.createCriteria().andGnoEqualTo(lastGno).andNickEqualTo(ukey);
		
		List<List<Integer>> lastLogList = new ArrayList<List<Integer>>();
		List<Luckylog> list = luckylogMapper.selectByExample(example);
		Iterator<Luckylog> iter = list.iterator();
		
		while(iter.hasNext()) {
			Luckylog log = iter.next();
			List<Integer> numsList = new ArrayList<Integer>();
			numsList.add(log.getNo1());
			numsList.add(log.getNo2());
			numsList.add(log.getNo3());
			numsList.add(log.getNo4());
			numsList.add(log.getNo5());
			numsList.add(log.getNo6());
			
			lastLogList.add(numsList);
		}
		
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		Iterator<List<Integer>> iter2 = lastLogList.iterator();
		while(iter2.hasNext()) {
			List<Integer> nums = iter2.next();
			
			int luckyCount = 0;
			for(int i=0;i<nums.size();i++) {
				int no = nums.get(i);
				if(lastInfo.getNo1() == no) {
					luckyCount++;
				}
				if(lastInfo.getNo2() == no) {
					luckyCount++;
				}
				if(lastInfo.getNo3() == no) {
					luckyCount++;
				}
				if(lastInfo.getNo4() == no) {
					luckyCount++;
				}
				if(lastInfo.getNo5() == no) {
					luckyCount++;
				}
				if(lastInfo.getNo6() == no) {
					luckyCount++;
				}
			}
			if(luckyCount > 2) {
				resultList.add(nums);
			}
			//System.err.println("luckyCount:"+luckyCount);
		}
		
		//System.err.println(new JSONArray(lastLogList));
		return resultList;
	}


}
