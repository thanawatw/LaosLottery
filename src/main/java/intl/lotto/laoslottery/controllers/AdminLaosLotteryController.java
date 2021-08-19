package intl.lotto.laoslottery.controllers;

import intl.lotto.laoslottery.entities.LotteryPrize;
import intl.lotto.laoslottery.repositories.LotteryPrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminLaosLotteryController {

    @Autowired
    LotteryPrizeRepository lotteryPrizeRepo;

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("formLotteryPrize", new LotteryPrize());
        model.addAttribute("lotteryPrizes", lotteryPrizeRepo.findAll(Sort.by(Sort.Direction.DESC, "drawDate")));
        return "admin";
    }

    @PostMapping("/admin/lottery-prize/save")
    public ResponseEntity<?> saveLotteryPrize(LotteryPrize lotteryPrize) {
        try {
            if (lotteryPrize.getId() != null) {
                Optional<LotteryPrize> dbOpt = lotteryPrizeRepo.findById(lotteryPrize.getId());
                if (dbOpt.isPresent()) {
                    LotteryPrize lotteryPrizeDb = dbOpt.get();
                    lotteryPrizeDb.setDrawDate(lotteryPrize.getDrawDate());
                    lotteryPrizeDb.setFiveDigitsPrize(lotteryPrize.getFiveDigitsPrize());
                    lotteryPrizeDb.setTwoDigitsPrize(lotteryPrize.getTwoDigitsPrize());
                    lotteryPrizeRepo.save(lotteryPrizeDb);
                } else {
                    lotteryPrizeRepo.save(lotteryPrize);
                }
            } else {
                lotteryPrizeRepo.save(lotteryPrize);
            }
            return new ResponseEntity<>("บันทึกข้อมูลสำเร็จ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("บันทึกข้อมูลไม่สำเร็จ", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/admin/lottery-prize/delete")
    public ResponseEntity<?> deleteLotteryPrize(@RequestParam Long id) {
        try {
            lotteryPrizeRepo.deleteById(id);
            return new ResponseEntity<>("ลบข้อมูลสำเร็จ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ลบข้อมูลไม่สำเร็จ", HttpStatus.BAD_REQUEST);
        }
    }

}

