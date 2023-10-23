package main.prj321x_asm01_duongptfx19035.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_donation")
public class UserDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created")
    private String created;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private int money;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private Donation donation;

}
