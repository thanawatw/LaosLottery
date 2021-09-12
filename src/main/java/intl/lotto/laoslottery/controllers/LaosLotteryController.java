package intl.lotto.laoslottery.controllers;

import intl.lotto.laoslottery.entities.LotteryPrize;
import intl.lotto.laoslottery.repositories.LotteryPrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class LaosLotteryController {

    @Autowired
    LotteryPrizeRepository lotteryPrizeRepo;

    @GetMapping("")
    public String index(Model model) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Optional<LotteryPrize> lpOpt = lotteryPrizeRepo.findByDrawDate(formatter.parse(formatter.format(new Date())));
        model.addAttribute("todayLotteryPrize", lpOpt.isPresent() ? lpOpt.get() : new LotteryPrize());
        model.addAttribute("lotteryPrizes", lotteryPrizeRepo.findAll(Sort.by(Sort.Direction.DESC, "drawDate")));
        return "index";
    }

}

