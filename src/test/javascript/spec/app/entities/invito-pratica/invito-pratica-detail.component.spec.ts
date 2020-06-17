import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoPraticaDetailComponent } from 'app/entities/invito-pratica/invito-pratica-detail.component';
import { InvitoPratica } from 'app/shared/model/invito-pratica.model';

describe('Component Tests', () => {
  describe('InvitoPratica Management Detail Component', () => {
    let comp: InvitoPraticaDetailComponent;
    let fixture: ComponentFixture<InvitoPraticaDetailComponent>;
    const route = ({ data: of({ invitoPratica: new InvitoPratica(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoPraticaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvitoPraticaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvitoPraticaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invitoPratica on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invitoPratica).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
