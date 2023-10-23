package main.prj321x_asm01_duongptfx19035.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "organization")
    @NotNull
    @NotEmpty
    private String organization;

    @Column(name = "phone")
    @NotNull
    @NotEmpty
    private String phone;

    @Column(name = "status")
    private String status;

    @Column(name = "created")
    private String created;

    @Column(name = "start")
    private String start;

    @Column(name = "end")
    private String end;

    @Column(name = "total")
    private int total;

    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL)
    private List<UserDonation> donations;
}
