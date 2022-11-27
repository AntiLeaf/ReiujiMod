# 杀戮尖塔 灵乌路空 Mod

本仓库是一个 [**杀戮尖塔**](https://store.steampowered.com/app/646570/Slay_the_Spire/) 模组，添加了一名新的可选角色 [**灵乌路空**](https://zh.moegirl.org/灵乌路空)。

灵乌路空是地灵殿中的一只地狱鸦，拥有操纵核聚变和核裂变的能力。

某天趁自己的主人——古明地觉不在，她打开了觉最近常玩的一款卡牌游戏……

## 依赖模组
* [ModTheSpire](https://github.com/kiooeht/ModTheSpire) ：杀戮尖塔 Mod 加载器
* [BaseMod](https://github.com/daviscook477/BaseMod) ：一个 API Mod，同时也提供了游戏内调试用命令行
* [StSLib](https://github.com/kiooeht/StSLib) ：一个供其他 Mod 使用的关键词和机制的集合

## 新机制

- **符卡**：**符卡** 是一系列强大且独特的能力牌或者具有 **消耗** 的牌。
  - **符卡** 牌每回合只能使用一张。
- **热度**：每次攻击时，会附带一次等同于 **热度** 层数的伤害（不视为攻击伤害），然后 **热度** 层数减少 1。
  - 附带伤害的目标与攻击目标相同，换言之，AOE 攻击附带的也是 AOE 伤害。
- **虚空之拥**：对卡牌施加可以使其耗能减少，但打出时会将对应数量的“虚空”洗入抽牌堆。
  - **虚空之拥** 至多可以叠加等同于卡牌原本耗能的层数。
  - 对于耗能为 X 的牌，每层 **虚空之拥** 可以使其效果 +1。
  - 打出一张牌后会清除其拥有的 **虚空之拥**。

### 其他关键词

- **补充**：抽到这张牌时，再抽一张牌。
- **回响**：带有 **回响** 的牌在一回合内可以打出多次。
  - 换言之，这张牌在打出后会移回手牌，直到回合结束后才会结算弃牌或 **消耗**。
  - 如果一张 **回响** 卡带有强化，则只有每回合第一次打出时才具有强化效果。

[//]: # (- **连续技**：标有 **连续技 X** 的牌拥有 X 段，换言之在一回合内至多可以 ***连续***打出 X 次。)
[//]: # (  - 每次打出后，这张牌都会变化为下一段。离开手牌后，会变化回第一段。)
[//]: # (  - 如果连续打出不足 X 次后就打出其他牌或结束回合，则这张牌会进入弃牌堆，或者被 **消耗**（如果这张牌带有 **消耗**）。)
[//]: # (- **脱离**：当这张牌未被打出而离开手牌时，触发某些效果。)

## 致谢
  - Thanks to the [Marisa Mod](https://github.com/lf201014/STS_ThMod_MRS), which teaches me how to develop a mod of STS.
