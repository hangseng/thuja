
package com.thuja.modules.system.service.dto;

import com.thuja.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
public class MenuSmallDto  implements Serializable {

    private Long id;

    private String name;

    private String permission;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuSmallDto menuDto = (MenuSmallDto) o;
        return Objects.equals(id, menuDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
