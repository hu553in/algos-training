-- https://leetcode.com/problems/biggest-single-number/

with nums_and_nulls as (select null as num

                        union

                        select num
                        from MyNumbers
                        group by num
                        having count(num) = 1)
select num
from nums_and_nulls
order by num desc nulls last limit 1;
