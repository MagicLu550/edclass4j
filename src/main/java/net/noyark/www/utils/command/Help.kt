package net.noyark.www.utils.command

import net.noyark.www.utils.Message
import java.util.*


class Help constructor(var map:Map<String,CommandBase>): CommandBase{

    /**
     * help command
     */
    override fun execute(args: Array<out String>?):Any {
        if(args!!.isNotEmpty()){
            for(arg in args) {
                Message.info(Arrays.toString(map.get(arg)!!.usage()))
            }
        }else{
            for(entry in map){
                Message.info("-----");
                var cmd = entry.key
                val cmdobj = entry.value
                var usages =  cmdobj.usage()
                Message.info(cmd)
                for(usage in usages){
                    Message.info(usage)
                }
            }
        }
        return ""
    }

    override fun usage(): Array<String> {
        return arrayOf("用于帮助")
    }


}