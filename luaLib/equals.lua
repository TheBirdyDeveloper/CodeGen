function equals(o1, o2)
    if o1 == o2 then return true end
    local o1Type = type(o1)
    local o2Type = type(o2)
    if o1Type ~= o2Type then return false end

    local res = equals(o1.hd,o2.hd)
    res = res and equals(o1.tl,o2.tl)
    return res
end

